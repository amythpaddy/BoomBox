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
public class AlbumFragment1 extends Fragment{
    private String title;
    private int page;

    public static AlbumFragment createFragment(int page , String title){
        AlbumFragment albumFragment1 = new AlbumFragment();
        Bundle args = new Bundle();
        args.putInt("pageInt" , page);
        args.putString("pageName" , title);
        albumFragment1.setArguments(args);

        return albumFragment1;
    }

    @Override
    public  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("pageInt");
        title = getArguments().getString("pageName");
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_album, container , false);

        return view;
    }
}
