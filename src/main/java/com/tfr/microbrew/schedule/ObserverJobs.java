package com.tfr.microbrew.schedule;

import com.tfr.microbrew.config.BrewStep;
import com.tfr.microbrew.helper.BatchHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.tfr.microbrew.dao.BrewHouse.*;

/**
 *
 * Created by Erik on 4/11/2017.
 */
@Component
public class ObserverJobs {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    public void printCurrentStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n-------------------------------------\n");
        sb.append("------------Batch Status-------------\n");
        sb.append(String.format("Prepared Batches   : %s\n", PREPARED_BATCHES.size()));
        PREPARED_BATCHES.forEach(b -> sb.append(String.format("\t\t--%s\n", b.getName())));
        sb.append(String.format("Mashing Batches    : %s\n", MASHING_BATCHES.size()));
        MASHING_BATCHES.forEach(b -> sb.append(String.format("\t\t--%s %s\n", b.getName(), BatchHelper.getStepTimeRemaining(b, b.getStepTime(BrewStep.MASH)))));
        sb.append(String.format("Boiling Batches    : %s\n", BOILING_BATCHES.size()));
        BOILING_BATCHES.forEach(b -> sb.append(String.format("\t\t--%s %s\n", b.getName(), BatchHelper.getStepTimeRemaining(b, b.getStepTime(BrewStep.BOIL)))));
        sb.append(String.format("Fermenting Batches : %s\n", FERMENTING_BATCHES.size()));
        FERMENTING_BATCHES.forEach(b -> sb.append(String.format("\t\t--%s %s\n", b.getName(), BatchHelper.getStepTimeRemaining(b, b.getStepTime(BrewStep.FERMENT)))));
        sb.append(String.format("Carbonating Batches: %s\n", CARBONATING_BATCHES.size()));
        CARBONATING_BATCHES.forEach(b -> sb.append(String.format("\t\t--%s %s\n", b.getName(), BatchHelper.getStepTimeRemaining(b, b.getStepTime(BrewStep.CARBONATE)))));

        sb.append("----------Current Inventory----------\n");
        INVENTORY.entrySet().forEach(e -> sb.append(
                String.format("%s : %s gal\n", e.getKey(), e.getValue())));
        sb.append("-------------------------------------");
        logger.debug(sb.toString());
    }

}
