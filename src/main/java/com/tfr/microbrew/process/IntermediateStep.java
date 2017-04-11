package com.tfr.microbrew.process;

import com.tfr.microbrew.config.BrewStep;
import com.tfr.microbrew.helper.BatchHelper;
import com.tfr.microbrew.model.Batch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * Created by Erik on 4/11/2017.
 */
public class IntermediateStep implements ProcessStep {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Collection<Batch> batchesInStep;

    private ProcessStep nextStep;
    private ProcessStep previousStep;

    private final BrewStep step;

    private final int maxCapacity;

    public IntermediateStep(BrewStep step, Collection<Batch> batchesInStep, int maxCapacity) {
        this.step = step;
        this.batchesInStep = batchesInStep;
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
        this.nextStep = processStep;
    }

    @Override
    public ProcessStep getNextStep() {
        return this.nextStep;
    }

    @Override
    public void add(Batch batch) {
        batch.setStepStartTime();
        batchesInStep.add(batch);
    }

    @Override
    public void process() {
        List<Batch> toRemove = new ArrayList<>();
        batchesInStep.forEach(b -> {
            if(nextStep.hasAvailableCapacity()) {
                if(BatchHelper.isStepTimeElapsed(b, step)) {
                    logger.debug(String.format("Moving batch %b to next step: %s", b, nextStep.getStep()));
                    toRemove.add(b);
                    nextStep.add(b);
                }
            }
        });
        batchesInStep.removeAll(toRemove);
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
        return "IntermediateStep{" +
                "step=" + step +
                '}';
    }
}
