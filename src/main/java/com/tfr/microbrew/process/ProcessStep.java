package com.tfr.microbrew.process;

import com.tfr.microbrew.config.BrewStep;
import com.tfr.microbrew.model.Batch;

/**
 *
 * Created by Erik on 4/6/2017.
 */
public interface ProcessStep {

    void setPreviousStep(ProcessStep processStep);
    ProcessStep getPreviousStep();

    void setNextStep(ProcessStep processStep);
    ProcessStep getNextStep();

    void add(Batch batch);
    void process();

    boolean hasAvailableCapacity();

    BrewStep getStep();

}
