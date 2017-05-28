package com.example.saif.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saif.bakingapp.R;
import com.example.saif.bakingapp.callbacks.IngredientCallback;
import com.example.saif.bakingapp.model.Ingredient;

import java.util.List;

/**
 * Created by Mosaad on 24/05/2017.
 */

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.ViewHolder> {
    List<Ingredient> mIngredients;
    Context mContext;

    public IngredientListAdapter(Context context,List<Ingredient> ingredients){
        mContext = context;
        mIngredients = ingredients;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.ingredient_item,parent,false);
        Toast.makeText(mContext,"adapter",Toast.LENGTH_LONG).show();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Ingredient ingredient = mIngredients.get(position);
        Toast.makeText(mContext,"allal8999",Toast.LENGTH_LONG).show();

        holder.ingredientTv.setText(ingredient.getIngredient());
        holder.quantity.setText((int) ingredient.getQuantity());
        holder.measure.setText(ingredient.getMeasure());

    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView ingredientTv;
        TextView measure;
        TextView quantity;

        public ViewHolder(View itemView) {
            super(itemView);
            ingredientTv = (TextView) itemView.findViewById(R.id.ingredient);
            measure = (TextView) itemView.findViewById(R.id.measure);
            quantity = (TextView) itemView.findViewById(R.id.quantity);
        }
    }
    public void setData (List<Ingredient> ingredients){

        mIngredients = ingredients;
        Toast.makeText(mContext,mIngredients.size(),Toast.LENGTH_LONG).show();
        notifyDataSetChanged();
    }
}
