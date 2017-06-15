package com.example.saif.bakingapp;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.example.saif.bakingapp.Utils.Constants;
import com.example.saif.bakingapp.Utils.Global;
import com.example.saif.bakingapp.adapters.IngredientListAdapter;
import com.example.saif.bakingapp.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Saif on 24/05/2017.
 */

public class IngredientsListFragment extends Fragment{
    List<Ingredient> ingredients;
    IngredientListAdapter mIngredientAdapter;
    @BindView(R.id.ingredient_list) RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_ingredients_list,container,false);
        ButterKnife.bind(this,rootView);
        ingredients = new ArrayList<>();
        mIngredientAdapter = new IngredientListAdapter(getActivity(),ingredients);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mIngredientAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LayoutManager.getOrientation());

        recyclerView.addItemDecoration(dividerItemDecoration);
        mIngredientAdapter.setData(getIngredients());
        return rootView;
    }
    public IngredientsListFragment (){

    }
    public List<Ingredient> getIngredients( ){

      List<Ingredient> ingredientList =  new Select().from(Ingredient.class)
                 .where("Recipe = ?", Global.getgId())
                .execute();
        return ingredientList;
    }



}
