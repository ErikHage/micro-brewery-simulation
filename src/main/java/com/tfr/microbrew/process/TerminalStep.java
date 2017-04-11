package com.tfr.microbrew.process;

import com.tfr.microbrew.config.BrewStep;
import com.tfr.microbrew.model.Batch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.tfr.microbrew.dao.BrewHouse.IN_PROGRESS_BATCHES;

/**
 *
 * Created by Erik on 4/11/2017.
 */
public class TerminalStep implements ProcessStep {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Map<String, Double> inventory;

    private ProcessStep previousStep;

    private final BrewStep step;

    private final double maxCapacity;

    public TerminalStep(BrewStep step, Map<String, Double> inventory, double maxCapacity) {
        this.step = step;
        this.inventory = inventory;
        this.maxCapacity = maxCapacity;
    }

    @Override
    public void setPreviousStep(ProcessStep processStep) {
        this.previousStep = processStep;
    }

    @Override
    public ProcessStep getPreviousStep() {
        return this.previousStep;
    }

    @Override
    public void setNextStep(ProcessStep processStep) {
        //do nothing
    }

    @Override
    public ProcessStep getNextStep() {
        return null;
    }

    @Override
    public void add(Batch batch) {
        if(inventory.containsKey(batch.getName())) {
            inventory.put(batch.getName(), inventory.get(batch.getName()) + batch.getVolume());
        } else {
            inventory.put(batch.getName(), batch.getVolume());
        }
        IN_PROGRESS_BATCHES.remove(batch);
        logger.debug(String.format("Batch %s completed and stored in inventory", batch));
    }

    @Override
    public void process() {
        //does nothing
    }

    @Override
    public boolean hasAvailableCapacity() {
        Double currentInventory = 0.0;
        currentInventory = inventory.values()
                                    .stream()
                                    .reduce(currentInventory, Double::sum);
        return currentInventory < maxCapacity;
    }

    @Override
    public BrewStep getStep() {
        return this.step;
    }
}
