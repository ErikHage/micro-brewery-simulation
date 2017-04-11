package com.tfr.microbrew.dao;

import com.google.common.collect.Lists;
import com.tfr.microbrew.model.Batch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import static com.tfr.microbrew.config.Constants.PRODUCT_NAMES;

/**
 *
 * Created by Erik on 4/11/2017.
 */
public class BrewHouse {

    public static final List<Batch> IN_PROGRESS_BATCHES = Lists.newArrayList();

    public static final Queue<Batch> PREPARED_BATCHES = new LinkedBlockingQueue<>();

    public static final List<Batch> MASHING_BATCHES = Lists.newArrayList();
    public static final List<Batch> BOILING_BATCHES = Lists.newArrayList();
    public static final List<Batch> FERMENTING_BATCHES = Lists.newArrayList();
    public static final List<Batch> CARBONATING_BATCHES = Lists.newArrayList();

    public static final Map<String, Double> INVENTORY = new HashMap<String, Double>() {{
        PRODUCT_NAMES.forEach(p -> put(p, 0.0));
    }};

}
