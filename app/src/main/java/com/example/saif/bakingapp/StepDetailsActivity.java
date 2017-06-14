package com.example.saif.bakingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Saif on 04/06/2017.
 */

public class StepDetailsActivity extends AppCompatActivity{
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);
        if (savedInstanceState==null) {
            StepDetailsFragment stepDetailsFragment = new StepDetailsFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.step_container, stepDetailsFragment);
        }
    }
}
