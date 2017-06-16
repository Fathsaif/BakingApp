package com.example.saif.bakingapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaCodec;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saif.bakingapp.Utils.Constants;
import com.example.saif.bakingapp.Utils.Global;
import com.example.saif.bakingapp.callbacks.StepDetailsCallback;
import com.example.saif.bakingapp.model.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.android.exoplayer2.mediacodec.MediaCodecInfo.TAG;

/**
 * Created by Saif on 04/06/2017.
 */

public class StepDetailsFragment extends Fragment implements ExoPlayer.EventListener{
    private SimpleExoPlayer mExoPlayer;
    private static MediaSessionCompat mMediaSession;
    private PlaybackStateCompat.Builder mStateBuilder;
    String stepDesc, videoUrl;
    List<Step> mSteps;
    boolean flag=false;
    Long stepNumber;
    private int mListIndex;
    @BindView(R.id.prev_btn)Button prevBtn;
    @BindView(R.id.next_btn)Button nextBtn;
    @BindView(R.id.stepNumber)TextView stepNumberTv;
    @BindView(R.id.step_description)TextView stepDescTv;
    @BindView(R.id.exo_player)SimpleExoPlayerView mPlayerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step_details,container,false);
        ButterKnife.bind(this,rootView);
        Bundle b = new Bundle();
        b = getActivity().getIntent().getExtras();

        if (b!=null&&savedInstanceState==null) {
            mSteps = b.getParcelableArrayList(Constants.stepList);
            int position = b.getInt(Constants.stepId);
            if (position!=0)
            setmListIndex(position);

        }
        if (savedInstanceState != null){

            mListIndex =   savedInstanceState.getInt("w");
            setmListIndex(mListIndex);
        }
        mSteps = b.getParcelableArrayList(Constants.stepList);
        Step step = mSteps.get(getmListIndex());
        stepDesc = step.getDescription();
        videoUrl = step.getVideoURL();
        stepNumber = step.getId();
        stepDescTv.setText(stepDesc);
        stepNumberTv.setText(stepNumber+"");
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                releasePlayer();
                if (mListIndex>0){
                    mListIndex--;
                }
                Step step = mSteps.get(mListIndex);
                stepDesc = step.getDescription();
                videoUrl = step.getVideoURL();
                stepNumber = step.getId();
                stepDescTv.setText(stepDesc);
                stepNumberTv.setText(stepNumber+"");
                initializeMediaSession();
                initializePlayer(videoUrl);
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                releasePlayer();
                if (mListIndex<mSteps.size()-1){
                    mListIndex++;

                }
                    Step step = mSteps.get(mListIndex);
                    stepDesc = step.getDescription();
                    videoUrl = step.getVideoURL();
                    stepNumber = step.getId();
                    stepDescTv.setText(stepDesc);
                    stepNumberTv.setText(stepNumber+"");
                    initializeMediaSession();
                    initializePlayer(videoUrl);

            }
        });
        initializeMediaSession();
        initializePlayer(videoUrl);

        return rootView;
    }

       private void initializePlayer(String mediaUrl) {
        if (mExoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();

            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);

            mExoPlayer.addListener(this);

            String userAgent = Util.getUserAgent(getActivity(), "BakingApp");
            MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(mediaUrl), new DefaultDataSourceFactory(
                    getActivity(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    private void initializeMediaSession() {
        mMediaSession = new MediaSessionCompat(getContext(), TAG);

        mMediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
        mMediaSession.setActive(true);

        mMediaSession.setMediaButtonReceiver(null);

        mStateBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE |
                                PlaybackStateCompat.ACTION_FAST_FORWARD|
                                PlaybackStateCompat.ACTION_SKIP_TO_NEXT|
                                PlaybackStateCompat.ACTION_REWIND);

        mMediaSession.setPlaybackState(mStateBuilder.build());

        mMediaSession.setCallback(new MySessionCallback());

        mMediaSession.setActive(true);

    }

    private void releasePlayer() {
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
        mMediaSession.setActive(false);
    }


    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if((playbackState == ExoPlayer.STATE_READY) && playWhenReady){
            mStateBuilder.setState(PlaybackStateCompat.STATE_PLAYING,
                    mExoPlayer.getCurrentPosition(), 1f);
        } else if((playbackState == ExoPlayer.STATE_READY)){
            mStateBuilder.setState(PlaybackStateCompat.STATE_PAUSED,
                    mExoPlayer.getCurrentPosition(), 1f);
        }
        mMediaSession.setPlaybackState(mStateBuilder.build());
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    private class MySessionCallback extends MediaSessionCompat.Callback {
        @Override
        public void onPlay() {
            mExoPlayer.setPlayWhenReady(true);
        }

        @Override
        public void onPause() {
            mExoPlayer.setPlayWhenReady(false);
        }

        @Override
        public void onSkipToPrevious() {
            mExoPlayer.seekTo(0);
        }
    }
    public static class MediaReceiver extends BroadcastReceiver {

        public MediaReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            MediaButtonReceiver.handleIntent(mMediaSession, intent);
        }
    }
public StepDetailsFragment(){

}
    public StepDetailsFragment(int index){
        mListIndex = index;
    }
    public int getmListIndex() {
        return mListIndex;
    }

    public void setmListIndex(int listIndex) {
        mListIndex = listIndex;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("w",mListIndex);
        Toast.makeText(getActivity(),"save instance = " + mListIndex,Toast.LENGTH_LONG).show();

    }
}
