package com.tfr.microbrew.helper;

import com.tfr.microbrew.config.BrewStep;
import com.tfr.microbrew.model.Batch;

/**
 *
 * Created by Erik on 4/11/2017.
 */
public class BatchHelper {

    public static boolean isStepTimeElapsed(Batch batch, long requiredTime) {
        return getElapsedTime(batch) > requiredTime;
    }

    public static boolean isStepTimeElapsed(Batch batch, BrewStep step) {
        return getElapsedTime(batch) > batch.getStepTime(step);
    }

    public static long getStepTimeRemaining(Batch batch, long requiredTime) {
        long elapsedTime = getElapsedTime(batch);
        return requiredTime - elapsedTime < 0 ? 0 : requiredTime - elapsedTime;
    }

    private static long getElapsedTime(Batch batch) {
        return System.currentTimeMillis() - batch.getStepStartTime();
    }

}
