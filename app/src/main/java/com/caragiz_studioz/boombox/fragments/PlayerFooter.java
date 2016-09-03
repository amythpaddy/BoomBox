package com.caragiz_studioz.boombox.fragments;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.caragiz_studioz.boombox.DetailedAlbumActivity;
import com.caragiz_studioz.boombox.PlayerDetailed;
import com.caragiz_studioz.boombox.R;
import com.caragiz_studioz.boombox.dataObjects.AlbumCardHolder;
import com.caragiz_studioz.boombox.dataObjects.GlobalResource;

/**
 * Created by caragiz on 01-09-2016.
 */
public class PlayerFooter extends Fragment {

    View albumArt;
    View playButton;
    View trackName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_fragment, container, false);
        albumArt = view.findViewById(R.id.albumArt);
        playButton = view.findViewById(R.id.playButton);
        trackName = view.findViewById(R.id.trackName);

        ((TextView) trackName).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Status in OnCLL:", "Player Touched");
                Activity activity = getActivity();
                Toast.makeText(activity, "Player touched", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(activity, PlayerDetailed.class);

                //View view= linearLayoutManager.findViewByPosition(GlobalResource.albumCardPosition);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, Pair.create(albumArt, "albumartplayer"), Pair.create(trackName, "trackname"), Pair.create(playButton, "playbutton"));
                //ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity , GlobalResource.albumArt , "albumart");
                activity.startActivity(intent, options.toBundle());
            }
        });


        return view;
    }

    public void onLayoutClick(View view) {
        Log.i("Status :", "Player Touched");
        Activity activity = getActivity();
        Toast.makeText(activity, "Player touched", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(activity, PlayerDetailed.class);

        //View view= linearLayoutManager.findViewByPosition(GlobalResource.albumCardPosition);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, Pair.create(albumArt, "albumartplayer"), Pair.create(trackName, "trackname"), Pair.create(playButton, "playbutton"));
        //ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity , GlobalResource.albumArt , "albumart");
        activity.startActivity(intent, options.toBundle());
    }
}
