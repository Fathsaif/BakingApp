package com.example.saif.bakingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.saif.bakingapp.adapters.IngredientListAdapter;
import com.example.saif.bakingapp.callbacks.IngredientCallback;
import com.example.saif.bakingapp.model.Ingredient;
import com.example.saif.bakingapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mosaad on 24/05/2017.
 */

public class IngredientsListFragment extends Fragment implements IngredientCallback{
    RecyclerView recyclerView;
    List<Ingredient> ingredients;
    IngredientListAdapter mIngredientAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_ingredients_list,container,false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.ingredient_list);
        ingredients = new ArrayList<>();
        mIngredientAdapter = new IngredientListAdapter(getActivity(),ingredients);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mIngredientAdapter);
        return rootView;
    }

    @Override
    public void getIngredientBtn(int position,List<Recipe> recipeList) {
        mIngredientAdapter.setData(recipeList.get(position).getIngredients());

    }
}
