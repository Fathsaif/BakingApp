package com.example.saif.bakingapp;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.example.saif.bakingapp.Utils.Constants;
import com.example.saif.bakingapp.Utils.Global;
import com.example.saif.bakingapp.callbacks.IngredientCallback;
import com.example.saif.bakingapp.callbacks.StepCallback;
import com.example.saif.bakingapp.model.Ingredient;
import com.example.saif.bakingapp.model.Recipe;
import com.example.saif.bakingapp.model.Step;
import com.example.saif.bakingapp.rest.ApiClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements IngredientCallback,StepCallback {
    FragmentManager fragmentManager;
    private Call<List<Recipe>> recipesCall;
    @BindView(R.id.failed)TextView errorMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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

                        for (Ingredient ingredient : recipe.getIngredients()) {
                            ingredient.recipe = recipe;
                            ingredient.save();
                        }
                        for (Step step : recipe.getSteps()) {
                            step.recipe = recipe;
                            step.save();
                        }
                        }
                    finish();
                    startActivity(getIntent());
                }

            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                errorMessage.setVisibility(View.VISIBLE);
            }
        });
    }

    public void startIngredientActivity (){
        Intent intent = new Intent(this,IngredientsListActivity.class);
        startActivity(intent);
    }

    public void startStepActivity(Bundle b){
        Intent intent = new Intent(this,StepListActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }
    @Override
    public void startActivity() {
        startIngredientActivity();

    }
    public void sendDatatoWidget(){
        Intent i = new Intent(this,IngredientWidgetProvider.class);
        i.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        sendBroadcast(i);
    }
    @Override
    public void getIngredientBtn(Long recipeId) {
        Global.setgId(recipeId);
        sendDatatoWidget();
    }

    @Override
    public void getStepBtn(Long recipeId) {
        List<Step> steps = new Select().from(Step.class)
                .where("Recipe = ?", recipeId)
                .execute();
        Bundle b = new Bundle();
        b.putParcelableArrayList(Constants.stepList, (ArrayList<? extends Parcelable>)steps );
        startStepActivity(b);
        Global.setgId(recipeId);
    }

}
