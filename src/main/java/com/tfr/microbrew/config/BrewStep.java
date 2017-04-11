package com.tfr.microbrew.config;

import static com.tfr.microbrew.config.Constants.*;

/**
 *
 *
 * Created by Erik on 4/11/2017.
 */
public enum BrewStep {

    PREPARE(StepNames.PREPARE),
    MASH(StepNames.MASH),
    BOIL(StepNames.BOIL),
    FERMENT(StepNames.FERMENT),
    CARBONATE(StepNames.CARBONATE),
    PACKAGE(StepNames.PACKAGE);

    private String value;

    BrewStep(String value) {
        this.value= value;
    }

    public String value() {
        return this.value;
    }

}
