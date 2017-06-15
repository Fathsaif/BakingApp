package com.example.saif.bakingapp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.saif.bakingapp.R;
import com.example.saif.bakingapp.callbacks.StepDetailsCallback;
import com.example.saif.bakingapp.model.Step;

import java.util.HashMap;
import java.util.List;

import wseemann.media.FFmpegMediaMetadataRetriever;

import static android.media.ThumbnailUtils.createVideoThumbnail;

/**
 * Created by Saif on 03/06/2017.
 */

public class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.ViewHolder> {

    private List<Step> mSteps;
    Context mContext;
    StepDetailsCallback mStepDetails ;


    public StepListAdapter(List<Step> mSteps, Context mContext,StepDetailsCallback stepDetailsCallback) {
        this.mSteps = mSteps;
        this.mContext = mContext;
        mStepDetails = stepDetailsCallback;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.step_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Step step = mSteps.get(position);
        Bitmap p = null;
        holder.stepDesc.setText(step.getShortDescription());
        String vidUrl = step.getVideoURL();
        String imageUrl = step.getThumbnailURL();
        if (vidUrl != null&& vidUrl.length() ==0){
            }

        if (imageUrl != null) {
            Glide.with(mContext).load(imageUrl).into(holder.stepImage);
        }

    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView stepDesc;
        ImageView stepImage;

        public ViewHolder(View itemView) {
            super(itemView);
            stepDesc = (TextView) itemView.findViewById(R.id.step_short_description);
            stepImage = (ImageView) itemView.findViewById(R.id.step_thumb);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            mStepDetails.onStepClicked( getAdapterPosition(),mSteps);

        }
    }
    public void setmSteps (List<Step> steps){
        mSteps = steps;
        notifyDataSetChanged();
    }

   }
