package com.tfr.microbrew.model;

/**
 *
 *
 * Created by Erik on 4/3/2017.
 */
public class Batch extends Recipe {

    private final int batchId;
    private long stepStartTime;

    public Batch(Integer batchId, Recipe recipe) {
        super(recipe.getName(),
              recipe.getMashTime(),
              recipe.getBoilTime(),
              recipe.getFermentationTime(),
              recipe.getCarbonationTime(),
              recipe.getVolume());
        this.batchId = batchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Batch batch = (Batch) o;

        return batchId == batch.batchId;
    }

    @Override
    public int hashCode() {
        return batchId;
    }

    public int getBatchId() {
        return batchId;
    }

    public long getStepStartTime() {
        return stepStartTime;
    }

    public void setStepStartTime() {
        this.stepStartTime = System.currentTimeMillis();
    }
}
