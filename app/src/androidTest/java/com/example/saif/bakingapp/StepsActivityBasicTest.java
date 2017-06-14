package com.example.saif.bakingapp;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;

/**
 * Created by Saif on 13/06/2017.
 */

public class StepsActivityBasicTest {
    @Rule
    public ActivityTestRule<StepListActivity> mActivityTestRule =
            new ActivityTestRule<>(StepListActivity.class);
}
