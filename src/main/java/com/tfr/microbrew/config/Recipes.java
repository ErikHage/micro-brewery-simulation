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

    Recipe CHECKS_AND_BALANCES_IPA = new Recipe(RecipeNames.CHECKS_AND_BALANCES_IPA, 90,90,10800,2880, 90);
    Recipe ROSIES_RED_ALE = new Recipe(RecipeNames.ROSIES_RED_ALE, 60,60,10800,2880, 90);
    Recipe COLD_BREWED_COFFEE_PORTER = new Recipe(RecipeNames.COLD_BREWED_COFFEE_PORTER, 60,60,12960,1440, 90);

    Map<String, Recipe> RECIPES = new HashMap<String, Recipe>() {{
        put(RecipeNames.CHECKS_AND_BALANCES_IPA, CHECKS_AND_BALANCES_IPA);
        put(RecipeNames.ROSIES_RED_ALE, ROSIES_RED_ALE);
        put(RecipeNames.COLD_BREWED_COFFEE_PORTER, COLD_BREWED_COFFEE_PORTER);
    }};

}
