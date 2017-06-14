package com.example.saif.bakingapp;

import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.activeandroid.query.Select;
import com.example.saif.bakingapp.adapters.RecipeListAdapter;
import com.example.saif.bakingapp.callbacks.IngredientCallback;
import com.example.saif.bakingapp.callbacks.StepCallback;
import com.example.saif.bakingapp.model.Ingredient;
import com.example.saif.bakingapp.model.Recipe;
import com.example.saif.bakingapp.rest.Services;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.saif.bakingapp.rest.ApiClient.getClient;

/**
 * Created by Saif on 18/05/2017.
 */

public class MainFragment extends Fragment{
    RecipeListAdapter mListAdapter;
    List<Recipe> mRecipes;
    @BindView(R.id.recipes_list)RecyclerView recyclerView;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);
        LinearLayoutManager vLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        ButterKnife.bind(this,rootView);
        recyclerView.setLayoutManager(vLayoutManager);
        mListAdapter = new RecipeListAdapter(getActivity(),mRecipes, (IngredientCallback) getActivity(), (StepCallback) getActivity());
        recyclerView.setAdapter(mListAdapter);
        mListAdapter.setRecipes(getmRecipes());
       // new FetchRecipeTask().execute();

        return rootView;

    }
    public MainFragment(){

    }

        public List<Recipe> getmRecipes (){
        return new Select().from(Recipe.class).orderBy("Recipe_id ASC").execute();
    }


}
