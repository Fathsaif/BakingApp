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
 * Created by Saif on 12/06/2017.
 */

public class ListWidgetServices extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Toast.makeText(getApplicationContext(),"remote",Toast.LENGTH_SHORT).show();

        return new WidgetDataProvider(intent,this);
    }
}
