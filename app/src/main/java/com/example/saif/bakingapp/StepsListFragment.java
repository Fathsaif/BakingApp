package com.example.saif.bakingapp;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.activeandroid.query.Select;
import com.example.saif.bakingapp.Utils.Constants;
import com.example.saif.bakingapp.Utils.Global;
import com.example.saif.bakingapp.Utils.NavigationUtil;
import com.example.saif.bakingapp.adapters.StepListAdapter;
import com.example.saif.bakingapp.callbacks.StepDetailsCallback;
import com.example.saif.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.saif.bakingapp.Utils.Constants.stepId;

/**
 * Created by Saif on 10/06/2017.
 */

public class StepsListFragment extends Fragment
{
    List<Step> stepList;
    StepListAdapter mStepAdapter;
    @BindView(R.id.step_list)RecyclerView stepRecyleView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_steps_list,container,false);
        ButterKnife.bind(this,rootView);
        mStepAdapter = new StepListAdapter(stepList,getActivity(), (StepDetailsCallback) getActivity());
        LinearLayoutManager LayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        stepRecyleView.setLayoutManager(LayoutManager);
        stepRecyleView.setHasFixedSize(true);
        stepRecyleView.setAdapter(mStepAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(stepRecyleView.getContext(),
                LayoutManager.getOrientation());
        stepRecyleView.addItemDecoration(dividerItemDecoration);
        mStepAdapter.setmSteps(getStepList());

        return rootView;
    }

public StepsListFragment(){

}
    private List<Step> getStepList(){
        return new Select().from(Step.class)
                .where("Recipe = ?", Global.getgId())
                .execute();
    }

}
