package com.example.saif.bakingapp;

import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

/**
 * Created by Mosaad on 18/05/2017.
 */

public class MainFragment extends Fragment {
    RecipeListAdapter mListAdapter;
    List<Recipe> mRecipes;
    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);
        LinearLayoutManager vLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recipes_list);
        recyclerView.setLayoutManager(vLayoutManager);
        mListAdapter = new RecipeListAdapter(getActivity(),mRecipes, (IngredientCallback) getActivity());
        recyclerView.setAdapter(mListAdapter);
        new FetchRecipeTask().execute();

        return rootView;

    }
    public MainFragment(){

    }



    public class FetchRecipeTask extends AsyncTask<Void,Void,List<Recipe>> {

        @Override
        protected List<Recipe> doInBackground(Void... params) {
            Retrofit retrofit = getClient();
            Services services = retrofit.create(Services.class);
            Call<List<Recipe>> recipeCall = services.discoverRecipes();
            try {
                Response<List<Recipe>> recipeResponse = recipeCall.execute();
                List<Recipe> recipes = (List<Recipe>) recipeResponse.body();
                return recipes;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Recipe> recipeList) {
            super.onPostExecute(recipeList);
            mListAdapter.setRecipes(recipeList);
            Ingredient ingredient = new Ingredient();

        }
    }
}
