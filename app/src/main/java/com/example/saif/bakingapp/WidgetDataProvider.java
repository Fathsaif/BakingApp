package com.example.saif.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.example.saif.bakingapp.Utils.Constants;
import com.example.saif.bakingapp.Utils.Global;
import com.example.saif.bakingapp.model.Ingredient;

import java.util.List;

/**
 * Created by Mosaad on 15/06/2017.
 */

public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {
    List<Ingredient> ingredientList ;
    private Context mContext=null;
    private int appWidgetId;
    private Intent intent;


    public WidgetDataProvider(Intent intent , Context context) {
        this.intent = intent;
        appWidgetId=intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
        mContext=context;
    }
    @Override
    public void onCreate() {
        ingredientList =  new Select().from(Ingredient.class)
                .where("Recipe = ?", Global.getgId())
                .execute();
    }

    @Override
    public void onDataSetChanged() {
        ingredientList =  new Select().from(Ingredient.class)
                .where("Recipe = ?", Global.getgId())
                .execute();

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return ingredientList.size();

    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews row=new RemoteViews(mContext.getPackageName(),
                R.layout.ingredient_item);
        Ingredient ingredient = ingredientList.get(position);
        String q = String.valueOf(ingredient.getQuantity());
        String name = ingredient.getIngredient();
        String measure = ingredient.getMeasure();
        row.setTextViewText(R.id.ingredient,name);
        row.setTextViewText(R.id.measure,measure);
        row.setTextViewText(R.id.quantity, q);

        Bundle extras = new Bundle();
        extras.putInt(Constants.iposition, position);
        Intent fillInIntent = new Intent(mContext,IngredientsListFragment.class);
        fillInIntent.putExtra(Constants.ingredient,name);
        fillInIntent.putExtra(Constants.measure,measure);
        fillInIntent.putExtra(Constants.quantity,q);
        fillInIntent.putExtras(extras);
        PendingIntent appPendingIntent = PendingIntent.getActivity(mContext, 0, fillInIntent, PendingIntent.FLAG_UPDATE_CURRENT);
      //  row.setPendingIntentTemplate(R.id.widget_ingredient_list,appPendingIntent);

        return row;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return ingredientList.get(position).getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
    public void setData (List<Ingredient> list){
        ingredientList = list;

    }
}
