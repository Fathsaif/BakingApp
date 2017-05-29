package com.example.saif.bakingapp.callbacks;

import com.example.saif.bakingapp.model.Ingredient;
import com.example.saif.bakingapp.model.Recipe;

import java.util.List;

/**
 * Created by Mosaad on 24/05/2017.
 */

public interface IngredientCallback {
    void getIngredientBtn(List<Ingredient> ingredients);
}
