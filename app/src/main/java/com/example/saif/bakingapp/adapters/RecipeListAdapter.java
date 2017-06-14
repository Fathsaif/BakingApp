package com.example.saif.bakingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.saif.bakingapp.IngredientsListActivity;
import com.example.saif.bakingapp.R;
import com.example.saif.bakingapp.callbacks.IngredientCallback;
import com.example.saif.bakingapp.callbacks.StepCallback;
import com.example.saif.bakingapp.model.Ingredient;
import com.example.saif.bakingapp.model.Recipe;

import java.util.List;

/**
 * Created by Saif on 18/05/2017.
 */

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {
    List<Recipe> recipes;
    Context mContext;
    IngredientCallback mCallback;
    StepCallback mStepCallback;

    public RecipeListAdapter(Context context,List<Recipe> recipeList,IngredientCallback callback,StepCallback stepCallback){
        mContext =context;
        recipes = recipeList;
        mCallback = callback;
        mStepCallback = stepCallback;
    }


        @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recipe_card,parent,false);
            ViewHolder v = new ViewHolder(view);
                   return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Recipe recipe = recipes.get(position);
        String title = recipe.getName();
        String imageUrl = recipe.getImage();
        if (imageUrl!=null && imageUrl.length()>0) {
            Glide.with(mContext).load(imageUrl).into(holder.recipeImage);
        }

        holder.recipeName.setText(title);

        holder.stepsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStepCallback.getStepBtn(recipes.get(position).getId());
            }
        });
        holder.ingredBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCallback.getIngredientBtn(recipes.get(position).getId());
                mCallback.startActivity();
                Toast.makeText(mContext,""+position,Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        if (recipes == null) {
            return 0;
        }

        return recipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView recipeName;
        ImageView recipeImage;
        Button ingredBtn;
        Button stepsBtn;

        public ViewHolder(final View itemView) {
            super(itemView);
            ingredBtn = (Button) itemView.findViewById(R.id.ingredients_btn);
            stepsBtn = (Button) itemView.findViewById(R.id.steps_btn);
            recipeName = (TextView) itemView.findViewById(R.id.recipe_name);
            recipeImage = (ImageView) itemView.findViewById(R.id.recipe_thumbnail);

        }

        }


    public void setLisenter (IngredientCallback lisenter){
        mCallback = lisenter;

    }

    public void setRecipes (List<Recipe> mRecipes){
        recipes = mRecipes;
        notifyDataSetChanged();

    }
}
