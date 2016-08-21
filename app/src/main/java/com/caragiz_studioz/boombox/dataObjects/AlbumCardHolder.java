package com.caragiz_studioz.boombox.dataObjects;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.caragiz_studioz.boombox.R;

/**
 * Created by caragiz on 12-08-2016.
 */
public class AlbumCardHolder extends RecyclerView.ViewHolder {
    public TextView text;
    public ImageView image;
    public AlbumCardHolder(View itemView) {
        super(itemView);
        text = (TextView)itemView.findViewById(R.id.info_text);
        image = (ImageView)itemView.findViewById(R.id.info_img);
    }
}
