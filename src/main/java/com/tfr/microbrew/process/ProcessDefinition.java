package com.tfr.microbrew.process;

import com.tfr.microbrew.config.BrewStep;
import com.tfr.microbrew.dao.BrewHouse;

import static com.tfr.microbrew.config.Constants.EquipmentLimits.*;

/**
 *
 * Created by Erik on 4/11/2017.
 */
public class ProcessDefinition {

    private final ProcessStep preparationStep = new PreparationStep(BrewStep.PREPARE, BrewHouse.PREPARED_BATCHES, MAX_PREPARED_BATCHES);

    private final ProcessStep mashingStep = new IntermediateStep(BrewStep.MASH, BrewHouse.MASHING_BATCHES, MAX_MASHING_BATCHES);
    private final ProcessStep boilingStep = new IntermediateStep(BrewStep.BOIL, BrewHouse.BOILING_BATCHES, MAX_BOILING_BATCHES);
    private final ProcessStep fermentationStep = new IntermediateStep(BrewStep.FERMENT, BrewHouse.FERMENTING_BATCHES, MAX_FERMENTING_BATCHES);
    private final ProcessStep carbonationStep = new IntermediateStep(BrewStep.CARBONATE, BrewHouse.CARBONATING_BATCHES, MAX_CARBONATING_BATCHES);

    private final ProcessStep packageStep = new TerminalStep(BrewStep.PACKAGE, BrewHouse.INVENTORY, MAX_INVENTORY);

    private static ProcessDefinition INSTANCE;

    private ProcessDefinition() {
        preparationStep.setNextStep(mashingStep);

        mashingStep.setPreviousStep(preparationStep);
        mashingStep.setNextStep(boilingStep);

        boilingStep.setPreviousStep(mashingStep);
        boilingStep.setNextStep(fermentationStep);

        fermentationStep.setPreviousStep(boilingStep);
        fermentationStep.setNextStep(carbonationStep);

        carbonationStep.setPreviousStep(fermentationStep);
        carbonationStep.setNextStep(packageStep);

        packageStep.setPreviousStep(carbonationStep);
    }

    public static ProcessDefinition getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ProcessDefinition();
        }
        return INSTANCE;
    }

    public ProcessStep getInitialStep() {
        return carbonationStep;
    }

}
