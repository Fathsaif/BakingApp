package com.example.saif.bakingapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.example.saif.bakingapp.adapters.RecipeListAdapter;
import com.example.saif.bakingapp.callbacks.IngredientCallback;
import com.example.saif.bakingapp.model.Ingredient;
import com.example.saif.bakingapp.model.Recipe;
import com.example.saif.bakingapp.model.Step;
import com.example.saif.bakingapp.rest.ApiClient;
import com.example.saif.bakingapp.rest.Services;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.saif.bakingapp.rest.ApiClient.getClient;
import static java.lang.Math.toIntExact;

public class MainActivity extends AppCompatActivity implements IngredientCallback {
    FragmentManager fragmentManager;
    private Call<List<Recipe>> recipesCall;

    int cast ( Long i){
        return (int)(long)i;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        MainFragment mainFragment = new MainFragment();
        fragmentManager.beginTransaction()
                .add(R.id.recipe_container,mainFragment).commit();
        recipesCall = ApiClient.getServices().discoverRecipes();
        recipesCall.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                List<Recipe> recipes ;
                recipes = response.body();
                if (new Select().from(Recipe.class).execute().size()==0) {
                    for (Recipe recipe : recipes) {
                        recipe.save();
                        Toast.makeText(getApplicationContext(), "sql = "+recipe.getId(), Toast.LENGTH_LONG).show();
                        List<Ingredient> ingredients = recipe.getIngredients();
                        List<Step> steps = recipe.getSteps();
                        //Ingredient ingredient = ingredients.get( (int) (long) recipe.getId()-1);
                       // Toast.makeText(getApplicationContext(), "steps = " +steps.size() +" ingred = " +ingredients.size(), Toast.LENGTH_LONG).show();
                        //ingredient.save();
                        for (Ingredient ingredient : recipe.getIngredients()) {
                            Toast.makeText(getApplicationContext(), "ingred = " +ingredient.getId(), Toast.LENGTH_LONG).show();
                            ingredient.recipe = recipe;
                            ingredient.save();
                        }
                        for (Step step : recipe.getSteps()) {
                            Toast.makeText(getApplicationContext(), "step = " +step.getId() , Toast.LENGTH_LONG).show();
                            step.recipe = recipe;
                            step.save();
                        }

                        }
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {

            }
        });
    }

    public void startIngredientActivity (){
        Intent intent = new Intent(this,IngredientsListActivity.class);
        startActivity(intent);
    }

    @Override
    public void startActivity() {
        startIngredientActivity();
        Toast.makeText(getApplicationContext(),"main",Toast.LENGTH_LONG).show();

    }

    @Override
    public void getIngredientBtn(Long recipeId) {
        Global.setgId(recipeId);
    }

   }
