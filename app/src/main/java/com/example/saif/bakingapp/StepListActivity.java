package com.example.saif.bakingapp;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.example.saif.bakingapp.Utils.Constants;
import com.example.saif.bakingapp.Utils.Global;
import com.example.saif.bakingapp.Utils.NavigationUtil;
import com.example.saif.bakingapp.adapters.StepListAdapter;
import com.example.saif.bakingapp.callbacks.StepDetailsCallback;
import com.example.saif.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

import static com.example.saif.bakingapp.Utils.Constants.stepId;

/**
 * Created by Saif on 03/06/2017.
 */

public class StepListActivity extends AppCompatActivity implements StepDetailsCallback{
   FragmentManager fragmentManager;
    private boolean mTwoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_list);
        fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.step_container)!= null){
            mTwoPane = true;
            Button next = (Button) findViewById(R.id.next_btn);
           // next.setVisibility(View.INVISIBLE);
            Button prev = (Button) findViewById(R.id.prev_btn);
            //prev.setVisibility(View.INVISIBLE);
            if (savedInstanceState==null){
            StepDetailsFragment stepDetailsFragment = new StepDetailsFragment();
            fragmentManager.beginTransaction().replace(R.id.step_container,stepDetailsFragment).commit();}

        }
        else {
            mTwoPane = false;
        }

        StepsListFragment stepsListFragment = new StepsListFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.steps_list_container,stepsListFragment).commit();

            }
    @Override
    public void onStepClicked(int Position, List<Step> steps) {
        if (mTwoPane){

            StepDetailsFragment stepDetailsFragment = new StepDetailsFragment();
            stepDetailsFragment.setmListIndex(Position);
            stepDetailsFragment.setRetainInstance(true);

            getSupportFragmentManager().beginTransaction().replace(R.id.step_container,stepDetailsFragment).commit();

        }
        else {
        Bundle b = new Bundle();
        b.putParcelableArrayList(Constants.stepList, (ArrayList<? extends Parcelable>) steps);
        b.putInt(stepId,Position );
        NavigationUtil.startStepDetails(this,b);

        }
    }



}
