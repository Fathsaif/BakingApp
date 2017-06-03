package com.example.saif.bakingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.example.saif.bakingapp.adapters.IngredientListAdapter;
import com.example.saif.bakingapp.adapters.RecipeListAdapter;
import com.example.saif.bakingapp.callbacks.IngredientCallback;
import com.example.saif.bakingapp.model.Ingredient;
import com.example.saif.bakingapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.recyclerview.R.attr.layoutManager;

/**
 * Created by Mosaad on 24/05/2017.
 */

public class IngredientsListFragment extends Fragment{
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
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LayoutManager.getOrientation());

        recyclerView.addItemDecoration(dividerItemDecoration);
        mIngredientAdapter.setData(getIngredients());
        return rootView;
    }
    public IngredientsListFragment (){

    }
    public List<Ingredient> getIngredients( ){
        int i = new Select().from(Ingredient.class)
                .execute().size();
      List<Ingredient> ingredientList =  new Select().from(Ingredient.class)
                 .where("Recipe = ?",Global.getgId())
                .execute();
        Toast.makeText(getContext(),i+" ;",Toast.LENGTH_LONG).show();
        return ingredientList;
    }


}
