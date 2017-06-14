package com.example.saif.bakingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Saif on 24/05/2017.
 */

public class IngredientsListActivity extends AppCompatActivity{
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients_list);
        fragmentManager = getSupportFragmentManager();
        IngredientsListFragment ingredientsListFragment = new IngredientsListFragment();
        fragmentManager.beginTransaction().add(R.id.ingredient_container,ingredientsListFragment).commit();
    }
}
