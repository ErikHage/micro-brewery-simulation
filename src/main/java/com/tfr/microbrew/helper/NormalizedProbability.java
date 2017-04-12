package com.tfr.microbrew.helper;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.*;

/**
*
* Created by Erik on 4/11/2017.
*/
public class NormalizedProbability<T> {

    private boolean isNormalized;
    private int roundingScale;
    private NavigableMap<Double, T> probability;
    private List<Item> items;

    public NormalizedProbability(int roundingScale) {
        this.items = new ArrayList<>();
        this.probability = new TreeMap<>();
        this.roundingScale = roundingScale;
        this.isNormalized = false;
    }

    public NormalizedProbability() {
        this(2);
    }

    public void add(Double weight, T value) {
        items.add(new Item(weight, value));
        refreshMap();
    }

    public T get(Double roll) {
        Map.Entry<Double, T> entry;
        roll = new BigDecimal(roll).setScale(roundingScale, BigDecimal.ROUND_HALF_UP).doubleValue();

        if(roll < 0 || roll > 100) {
            throw new InvalidParameterException("Value must be between 0 and 100");
        }

        entry = probability.ceilingEntry(roll);

        return entry.getValue();
    }

    private void refreshMap() {
        Double cumulative = 0.0;
        Double normalizationFactor = getNormalizationFactor();
        probability.clear();

        isNormalized = normalizationFactor != 1;

        for(Item i: items) {
            Double normalizaedWeight = i.weight * normalizationFactor;
            probability.put(cumulative += normalizaedWeight, i.value);
        }
    }


    public Double getNormalizationFactor() {
        Double normalization = 0.0;
        for(Item i: items) {
            normalization += i.weight;
        }
        return 100/normalization;
    }

    public boolean isNormalized() {
        return isNormalized;
    }

    public int getRoundingScale() {
        return roundingScale;
    }

    private class Item {
        Double weight;
        T value;
        private Item(Double weight, T value) {
            this.weight = weight;
            this.value = value;
        }
    }

}