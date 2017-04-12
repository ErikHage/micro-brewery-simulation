package com.tfr.microbrew.schedule;

import com.tfr.microbrew.process.ProcessDefinition;
import com.tfr.microbrew.process.ProcessStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * Created by Erik on 4/11/2017.
 */
@Component
public class ProcessJobs {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(fixedDelay = 5000)
    public void process() {
        ProcessStep processStep = ProcessDefinition.getInstance().getInitialStep();
        process(processStep);
    }

    private void process(ProcessStep processStep) {
        logger.debug(String.format("Processing step: %s", processStep));

        processStep.process();

        if(processStep.getPreviousStep() != null) {
            process(processStep.getPreviousStep());
        }
    }


}
