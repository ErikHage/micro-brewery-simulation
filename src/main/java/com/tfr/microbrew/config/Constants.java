package com.tfr.microbrew.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

/**
 *
 * Created by Erik on 4/6/2017.
 */
public interface Constants {

    Set<String> PRODUCT_NAMES = Sets.newHashSet(
            RecipeNames.CHECKS_AND_BALANCES_IPA,
            RecipeNames.ROSIES_RED_ALE,
            RecipeNames.COLD_BREWED_COFFEE_PORTER,
            RecipeNames.TRIPPLECANOE_AND_TYLER_TOO,
            RecipeNames.WIT_OF_THEIR_EYES,
            RecipeNames.AMBER_WAVES_OF_GRAIN,
            RecipeNames.SUMMER_IPA
    );

    List<String> STAPLE_PRODUCTS = Lists.newArrayList(
            RecipeNames.CHECKS_AND_BALANCES_IPA,
            RecipeNames.ROSIES_RED_ALE,
            RecipeNames.COLD_BREWED_COFFEE_PORTER,
            RecipeNames.AMBER_WAVES_OF_GRAIN
    );

    List<String> SEASONAL_PRODUCTS = Lists.newArrayList(
            RecipeNames.WIT_OF_THEIR_EYES,
            RecipeNames.SUMMER_IPA
    );

    List<String> LIMITED_PRODUCTS = Lists.newArrayList(
            RecipeNames.TRIPPLECANOE_AND_TYLER_TOO
    );

    interface RecipeNames {

        String CHECKS_AND_BALANCES_IPA = "Checks and Balances IPA";
        String ROSIES_RED_ALE = "Rosie's Red Ale";
        String COLD_BREWED_COFFEE_PORTER = "Cold Brewed Coffee Porter";
        String TRIPPLECANOE_AND_TYLER_TOO = "Tripplecanoe and Tyler Too";
        String WIT_OF_THEIR_EYES = "Wit of Their Eyes";
        String AMBER_WAVES_OF_GRAIN = "Amber Waves of Grain";
        String SUMMER_IPA = "Summer IPA";

    }

    interface EquipmentLimits {

        int MAX_IN_PROGRESS_BATCHES = 8;
        int MAX_IN_PROGRESS_BATCHES_SAME_RECIPE = 1;

        int MAX_PREPARED_BATCHES = 2;
        int MAX_MASHING_BATCHES = 1;
        int MAX_BOILING_BATCHES = 1;
        int MAX_FERMENTING_BATCHES = 8;
        int MAX_CARBONATING_BATCHES = 2;

        double INVENTORY_THRESHOLD = 100.0;
        double MAX_INVENTORY = 2000.0;

    }

    interface StepNames {

        String PREPARE = "Prepare";
        String MASH = "Mash";
        String BOIL = "Boil";
        String FERMENT = "Ferment";
        String CARBONATE = "Carbonate";
        String PACKAGE = "Package";

    }



}
