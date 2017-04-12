package com.tfr.microbrew.process;

import com.tfr.microbrew.config.BrewStep;
import com.tfr.microbrew.config.Constants;
import com.tfr.microbrew.config.Recipes;
import com.tfr.microbrew.model.Batch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.tfr.microbrew.config.Constants.EquipmentLimits.INVENTORY_THRESHOLD;
import static com.tfr.microbrew.config.Constants.EquipmentLimits.MAX_IN_PROGRESS_BATCHES;
import static com.tfr.microbrew.config.Constants.EquipmentLimits.MAX_IN_PROGRESS_BATCHES_SAME_RECIPE;
import static com.tfr.microbrew.config.Recipes.RECIPES;
import static com.tfr.microbrew.dao.BrewHouse.INVENTORY;
import static com.tfr.microbrew.dao.BrewHouse.IN_PROGRESS_BATCHES;

/**
 *
 * Created by Erik on 4/11/2017.
 */
public class PreparationStep implements ProcessStep {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final AtomicInteger BATCH_ID = new AtomicInteger(1000);

    private final Collection<Batch> batchesInStep;

    private ProcessStep nextStep;

    private final BrewStep step;

    private final int maxCapacity;

    public PreparationStep(BrewStep step, Collection<Batch> batchesInStep, int maxCapacity) {
        this.step = step;
        this.batchesInStep = batchesInStep;
        this.maxCapacity = maxCapacity;
    }

    @Override
    public void setPreviousStep(ProcessStep processStep) {
        //nothing
    }

    @Override
    public ProcessStep getPreviousStep() {
        return null;
    }

    @Override
    public void setNextStep(ProcessStep processStep) {
        this.nextStep = processStep;
    }

    @Override
    public ProcessStep getNextStep() {
        return this.nextStep;
    }

    @Override
    public void add(Batch batch) {
        batchesInStep.add(batch);
    }

    @Override
    public void process() {
        batchesInStep.forEach(b -> {
            if(nextStep.hasAvailableCapacity()) {
                logger.debug(String.format("Moving batch %s to next step: %s", b, nextStep.getStep()));
                batchesInStep.remove(b);
                nextStep.add(b);
            }
        });
        Batch batch = prepareNextBatch();
        if(batch != null) {
            batchesInStep.add(batch);
            IN_PROGRESS_BATCHES.add(batch);
            logger.debug(String.format("Prepared new batch: %s", batch));
        }
    }

    private Batch prepareNextBatch() {
        if(IN_PROGRESS_BATCHES.size() >= MAX_IN_PROGRESS_BATCHES) {
            return null;
        }

        Map<String, Integer> counts =
                INVENTORY.entrySet()
                         .stream()
                         .filter(e -> e.getValue() < INVENTORY_THRESHOLD)
                         .collect(Collectors.toMap(Map.Entry::getKey, this::getMatchCount));

        if(counts.size() == 0) {
            return null;
        }

        Map.Entry<String, Integer> entry =
                Collections.min(counts.entrySet(), Comparator.comparingDouble(Map.Entry::getValue));

        if(entry.getValue() < MAX_IN_PROGRESS_BATCHES_SAME_RECIPE) {
//            logger.debug("Entry key: " + entry.getKey());
//            logger.debug(RECIPES.get(entry.getKey()).toString());
            return new Batch(BATCH_ID.getAndIncrement(), RECIPES.get(entry.getKey()));
        }
        return null;
    }

    private int getMatchCount(Map.Entry<String, Double> entry) {
        final AtomicInteger count = new AtomicInteger(0);
        IN_PROGRESS_BATCHES.forEach(b -> {
            if(b.getName().equals(entry.getKey())) {
                count.getAndIncrement();
            }
        });
        return count.get();
    }


    @Override
    public boolean hasAvailableCapacity() {
        return batchesInStep.size() < maxCapacity;
    }

    @Override
    public BrewStep getStep() {
        return this.step;
    }

    @Override
    public String toString() {
        return "PreparationStep{" +
                "step=" + step +
                '}';
    }
}
