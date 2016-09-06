package com.caragiz_studioz.boombox.adapters;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caragiz_studioz.boombox.R;
import com.caragiz_studioz.boombox.dataObjects.AlbumCardHolder;
import com.caragiz_studioz.boombox.dataObjects.SongInfo;

import java.util.List;

/**
 * Created by caragiz on 12-08-2016.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<AlbumCardHolder> {

    @Override
    public AlbumCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout , parent , false);
        return new AlbumCardHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AlbumCardHolder holder, int position) {
        holder.text.setText(SongInfo.albumInfo.get(position).getAlbumName());

        if (SongInfo.albumInfo.get(position).getAlbumArt() != null)
            holder.image.setImageDrawable(SongInfo.albumInfo.get(position).getAlbumArt());


    }

    @Override
    public int getItemCount() {
        return SongInfo.albumInfo.size();
    }
}
