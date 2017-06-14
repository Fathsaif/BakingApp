package com.example.saif.bakingapp.callbacks;

import com.example.saif.bakingapp.model.Step;

import java.util.List;

/**
 * Created by Saif on 03/06/2017.
 */

public interface StepDetailsCallback {
    void onStepClicked(int Position, List<Step> steps);
}
