package com.caragiz_studioz.boombox.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caragiz_studioz.boombox.R;

/**
 * Created by caragiz on 28-07-2016.
 */
public class AlbumFragment extends Fragment{
    private String title;
    private int page;

    public static AlbumFragment createFragment(int page , String title){
        AlbumFragment albumFragment = new AlbumFragment();
        Bundle args = new Bundle();
        args.putInt("pageInt" , page);
        args.putString("pageName" , title);
        albumFragment.setArguments(args);

        return albumFragment;
    }

    @Override
    public  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("pageInt");
        title = getArguments().getString("pageName");
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.player_fragment, container, false);

        return view;
    }
}
