package com.caragiz_studioz.boombox.services;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.caragiz_studioz.boombox.DetailedAlbumActivity;
import com.caragiz_studioz.boombox.adapters.TrackListAdapter;
import com.caragiz_studioz.boombox.dataObjects.AlbumCardHolder;
import com.caragiz_studioz.boombox.dataObjects.GlobalResource;

/**
 * Created by caragiz on 24-08-2016.
 */

public class CardOnClickListenerService implements RecyclerView.OnItemTouchListener {

    Activity activity;
    GestureDetector gestureDetector;
    ImageView albumArt;
    TextView albumName;
    LinearLayoutManager linearLayoutManager;

    public CardOnClickListenerService(Activity activity, LinearLayoutManager linearLayoutManager){

        this.activity = activity;
        this.linearLayoutManager = linearLayoutManager;
        gestureDetector = new GestureDetector(activity.getApplicationContext() , new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onSingleTapUp(MotionEvent e){
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        if(gestureDetector.onTouchEvent(e)){
            Toast.makeText(activity.getApplicationContext() , "Touched" , Toast.LENGTH_SHORT).show();
            GlobalResource.albumCardPosition = rv.getChildAdapterPosition(rv.findChildViewUnder(e.getX(), e.getY()));
            int position = rv.getChildLayoutPosition(rv.findChildViewUnder(e.getX(), e.getY()));
            Intent intent = new Intent(activity , DetailedAlbumActivity.class);
            AlbumCardHolder vh = (AlbumCardHolder) rv.findViewHolderForAdapterPosition(GlobalResource.albumCardPosition);

            //View view= linearLayoutManager.findViewByPosition(GlobalResource.albumCardPosition);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity , Pair.create((View)vh.image, "albumart"), Pair.create((View)vh.text , "albumname"));
            //ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity , GlobalResource.albumArt , "albumart");
            activity.startActivity(intent , options.toBundle());
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

}
