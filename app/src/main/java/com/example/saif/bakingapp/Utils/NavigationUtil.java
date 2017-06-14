package com.example.saif.bakingapp.Utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.saif.bakingapp.StepDetailsActivity;

/**
 * Created by Saif on 03/06/2017.
 */

public class NavigationUtil {
    public void nxtBtn(int id){

    }
    public void preBrn(int id){

    }
    public static void startStepDetails(Activity activity, Bundle bundle){
        Intent intent = new Intent(activity, StepDetailsActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);

    }
}
