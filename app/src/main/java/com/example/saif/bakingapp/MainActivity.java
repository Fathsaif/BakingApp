package com.example.saif.bakingapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.example.saif.bakingapp.adapters.RecipeListAdapter;
import com.example.saif.bakingapp.callbacks.IngredientCallback;
import com.example.saif.bakingapp.model.Ingredient;
import com.example.saif.bakingapp.model.Recipe;
import com.example.saif.bakingapp.rest.Services;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.saif.bakingapp.rest.ApiClient.getClient;

public class MainActivity extends AppCompatActivity implements IngredientCallback {
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        MainFragment mainFragment = new MainFragment();
        fragmentManager.beginTransaction()
                .add(R.id.recipe_container,mainFragment).commit();
    }

    public void startIngredientActivity (){
        Intent intent = new Intent(this,IngredientsListActivity.class);
        startActivity(intent);
    }

    @Override
    public void getIngredientBtn(List<Ingredient> ingredients) {
        startIngredientActivity();
        Toast.makeText(getApplicationContext(),"main",Toast.LENGTH_LONG).show();


    }
}
