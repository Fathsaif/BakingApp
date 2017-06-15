package com.example.saif.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.saif.bakingapp.model.Ingredient;

import java.util.List;

/**
 * Created by Saif on 12/06/2017.
 */

public class IngredientWidgetProvider extends AppWidgetProvider {


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int i = 0; i < appWidgetIds.length; i++) {


            RemoteViews rv = new RemoteViews(context.getPackageName(),
                    R.layout.ingredient_widget);
            Toast.makeText(context,"update",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, ListWidgetServices.class);

            rv.setRemoteAdapter(appWidgetIds[i] , R.id.widget_ingredient_list, intent);
           Intent startActivityIntent = new Intent(context,MainActivity.class);

            PendingIntent startActivityPendingIntent = PendingIntent.getActivity(context, 0, startActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            rv.setPendingIntentTemplate(R.id.widget_ingredient_list, startActivityPendingIntent);

            rv.setEmptyView(R.id.widget_ingredient_list, R.id.empty_view);
            appWidgetManager.updateAppWidget(appWidgetIds[i], rv);

        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);

    }


    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager mgr;
        super.onReceive(context,intent);

        if (intent.getAction().equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            mgr = AppWidgetManager.getInstance(context);
            ComponentName componentName = new ComponentName(context,IngredientWidgetProvider.class);
            Toast.makeText(context,"recieved",Toast.LENGTH_SHORT).show();
            Log.e("received", intent.getAction());

            mgr.notifyAppWidgetViewDataChanged(mgr.getAppWidgetIds(componentName), R.id.widget_ingredient_list);
            this.onUpdate(context,mgr,mgr.getAppWidgetIds(componentName));

        }
    }
}
