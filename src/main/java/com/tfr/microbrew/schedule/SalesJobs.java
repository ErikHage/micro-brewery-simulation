package com.tfr.microbrew.schedule;

import com.tfr.microbrew.helper.NormalizedProbability;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static com.tfr.microbrew.config.Constants.LIMITED_PRODUCTS;
import static com.tfr.microbrew.config.Constants.SEASONAL_PRODUCTS;
import static com.tfr.microbrew.config.Constants.STAPLE_PRODUCTS;
import static com.tfr.microbrew.config.SalesConfig.*;
import static com.tfr.microbrew.dao.BrewHouse.INVENTORY;

/**
 *
 * Created by Erik on 4/11/2017.
 */
@Component
public class SalesJobs {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AtomicInteger sales = new AtomicInteger(0);
    private AtomicInteger unhappyCustomers = new AtomicInteger(0);

    private Random random = new Random();

    private static final NormalizedProbability<Double> VOLUME_PROBABILITY = new NormalizedProbability<Double>() {{
        add(VolumeProbability.PINT, ProductVolumes.PINT);
        add(VolumeProbability.FLIGHT, ProductVolumes.FLIGHT);
        add(VolumeProbability.HALF_GROWLER, ProductVolumes.HALF_GROWLER);
        add(VolumeProbability.GROWLER, ProductVolumes.GROWLER);
    }};

    private static final NormalizedProbability<List<String>> PRODUCT_PROBABILITY = new NormalizedProbability<List<String>>() {{
        add(ProductProbability.STAPLE, STAPLE_PRODUCTS);
        add(ProductProbability.SEASONAL, SEASONAL_PRODUCTS);
        add(ProductProbability.LIMITED, LIMITED_PRODUCTS);
    }};

    @Scheduled(fixedDelay = 100, initialDelay = 200000)
    public void makeSale() {
        Double volume = VOLUME_PROBABILITY.get(random.nextDouble()*100);
        List<String> productGroup = PRODUCT_PROBABILITY.get(random.nextDouble()*100);
        String product = productGroup.get(random.nextInt(productGroup.size()));

        if(INVENTORY.containsKey(product)) {
            Double currentStock = INVENTORY.get(product);
            if(currentStock < 0.0) {
                unhappyCustomers.getAndIncrement();
                logger.debug(String.format("Out of %s, Unhappy Customer!!!!!", product));
            } else {
                sales.getAndIncrement();
                INVENTORY.put(product, INVENTORY.get(product)-volume);
            }
        }
    }

    @Scheduled(fixedDelay = 10000, initialDelay = 200000)
    public void printSales() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n-------------SALES---------------\n");
        sb.append(String.format("Sales: %s   Fails: %s\n", sales.get(), unhappyCustomers.get()));
        sb.append("---------------------------------");
        logger.debug(sb.toString());
    }



}
