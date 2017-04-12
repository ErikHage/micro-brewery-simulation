package com.tfr.microbrew.config;

import com.tfr.microbrew.model.Recipe;

import java.util.HashMap;
import java.util.Map;

import static com.tfr.microbrew.config.Constants.RecipeNames;

/**
 * Recipes
 *
 * Created by Erik on 4/6/2017.
 */
public interface Recipes {

    Recipe CHECKS_AND_BALANCES_IPA = new Recipe(RecipeNames.CHECKS_AND_BALANCES_IPA, 90,90,108000,2880, 90);
    Recipe ROSIES_RED_ALE = new Recipe(RecipeNames.ROSIES_RED_ALE, 60,60,108000,2880, 90);
    Recipe COLD_BREWED_COFFEE_PORTER = new Recipe(RecipeNames.COLD_BREWED_COFFEE_PORTER, 60,60,129600,1440, 90);
    Recipe TRIPPLECANOE_AND_TYLER_TOO = new Recipe(RecipeNames.TRIPPLECANOE_AND_TYLER_TOO, 90, 90, 150000, 2880, 90);
    Recipe WIT_OF_THEIR_EYES = new Recipe(RecipeNames.WIT_OF_THEIR_EYES, 60, 60, 108000, 2880, 90);
    Recipe AMBER_WAVES_OF_GRAIN = new Recipe(RecipeNames.AMBER_WAVES_OF_GRAIN, 60, 60, 108000, 2880, 90);
    Recipe SUMMER_IPA = new Recipe(RecipeNames.SUMMER_IPA, 60, 90, 108000, 2880, 90);

    Map<String, Recipe> RECIPES = new HashMap<String, Recipe>() {{
        put(RecipeNames.CHECKS_AND_BALANCES_IPA, CHECKS_AND_BALANCES_IPA);
        put(RecipeNames.ROSIES_RED_ALE, ROSIES_RED_ALE);
        put(RecipeNames.COLD_BREWED_COFFEE_PORTER, COLD_BREWED_COFFEE_PORTER);
        put(RecipeNames.TRIPPLECANOE_AND_TYLER_TOO, TRIPPLECANOE_AND_TYLER_TOO);
        put(RecipeNames.WIT_OF_THEIR_EYES, WIT_OF_THEIR_EYES);
        put(RecipeNames.AMBER_WAVES_OF_GRAIN, AMBER_WAVES_OF_GRAIN);
        put(RecipeNames.SUMMER_IPA, SUMMER_IPA);
    }};

}
