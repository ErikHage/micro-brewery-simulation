package com.tfr.microbrew.model;

import com.tfr.microbrew.config.BrewStep;
import com.tfr.microbrew.process.ProcessStep;

import static com.tfr.microbrew.config.Constants.StepNames;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * Created by Erik on 4/3/2017.
 */
public class Recipe {

    private String name;
    private double volume;

    private Map<BrewStep, Long> stepTimes = new HashMap<>();

    public Recipe(String name, long mashTime, long boilTime, long fermentationTime, long carbonationTime, double volume) {
        this.name = name;
        this.volume = volume;
        this.stepTimes.put(BrewStep.MASH, mashTime);
        this.stepTimes.put(BrewStep.BOIL, boilTime);
        this.stepTimes.put(BrewStep.FERMENT, fermentationTime);
        this.stepTimes.put(BrewStep.CARBONATE, carbonationTime);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", volume=" + volume +
                ", stepTimes=" + stepTimes +
                '}';
    }

    public long getStepTime(BrewStep step) {
        return stepTimes.get(step);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMashTime() {
        return stepTimes.get(BrewStep.MASH);
    }

    public void setMashTime(long mashTime) {
        this.stepTimes.put(BrewStep.MASH, mashTime);
    }

    public long getBoilTime() {
        return stepTimes.get(BrewStep.BOIL);
    }

    public void setBoilTime(long boilTime) {
        this.stepTimes.put(BrewStep.BOIL, boilTime);
    }

    public long getFermentationTime() {
        return stepTimes.get(BrewStep.FERMENT);
    }

    public void setFermentationTime(long fermentationTime) {
        this.stepTimes.put(BrewStep.FERMENT, fermentationTime);
    }

    public long getCarbonationTime() {
        return stepTimes.get(BrewStep.CARBONATE);
    }

    public void setCarbonationTime(long carbonationTime) {
        this.stepTimes.put(BrewStep.CARBONATE, carbonationTime);
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
