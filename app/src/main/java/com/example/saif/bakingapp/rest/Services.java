package com.example.saif.bakingapp.rest;

import com.example.saif.bakingapp.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mosaad on 20/05/2017.
 */

public interface Services {
    @GET("baking.json")
    Call<List<Recipe>> discoverRecipes();
}
