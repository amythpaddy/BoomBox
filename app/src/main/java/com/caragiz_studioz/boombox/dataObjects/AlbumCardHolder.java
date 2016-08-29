package com.caragiz_studioz.boombox.dataObjects;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.caragiz_studioz.boombox.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caragiz on 12-08-2016.
 */
public class AlbumCardHolder extends RecyclerView.ViewHolder {
    public TextView text;
    public ImageView image;
    static public List<View> textViewList = new ArrayList<>();
    static public List<View> imageViewList = new ArrayList<>();
    public AlbumCardHolder(View itemView) {
        super(itemView);
        text = (TextView)itemView.findViewById(R.id.albumName);
        image = (ImageView)itemView.findViewById(R.id.albumArt);

        textViewList.add((View)text);
        imageViewList.add((View)image);
    }


    public static View getTextViewName(int position){
        Log.i("Size is :", String.valueOf(textViewList.size()));
        Log.i("Position is :", String.valueOf(position));
        return textViewList.get(position%textViewList.size());
    }

    public static View getImageViewName(int position){
        return imageViewList.get(position%textViewList.size());
    }
}
