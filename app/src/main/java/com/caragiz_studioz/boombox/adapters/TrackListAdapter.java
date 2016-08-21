package com.caragiz_studioz.boombox.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.caragiz_studioz.boombox.R;
import com.caragiz_studioz.boombox.dataObjects.SongInfo;
import com.caragiz_studioz.boombox.dataObjects.TrackData;
import com.caragiz_studioz.boombox.dataObjects.TrackListModel;

import java.util.ArrayList;

/**
 * Created by caragiz on 09-08-2016.
 */
public class TrackListAdapter extends BaseAdapter implements DialogInterface.OnClickListener{
    private Activity activity;
    private static LayoutInflater inflater = null;
    public Resources resources;

    TrackData tempValues = null;
    int i = 0;

    public TrackListAdapter(Activity activity , Resources resources){
        this.activity = activity;
        this.resources = resources;

        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if(SongInfo.trackInfo.size()<=0)
            return 1;
        return SongInfo.trackInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder{
        public TextView albumName;
        public TextView artistName;
        public TextView trackName;
        public ImageView albumArt;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if(convertView == null) {
            view = inflater.inflate(R.layout.titlelist, null);
            holder = new ViewHolder();
            holder.albumArt = (ImageView) view.findViewById(R.id.albumArt);
            holder.trackName = (TextView) view.findViewById(R.id.trackName);
            holder.albumName = (TextView) view.findViewById(R.id.albumName);
            holder.artistName = (TextView) view.findViewById(R.id.artistName);

            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        if(SongInfo.trackInfo.size()<0){
            holder.trackName.setText("No Data");
            holder.albumName.setText("No Data");
            holder.artistName.setText("No Data");
        }else{
            tempValues = null;
            tempValues = SongInfo.trackInfo.get(position);
            holder.trackName.setText(tempValues.getTitle());
            holder.albumName.setText(tempValues.getAlbum());
            holder.artistName.setText(tempValues.getArtist());

            view.setOnClickListener(new OnItemCLickListener(position));
        }
        view.setElevation(10f);
        return view;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Log.v("CustomAdapter", "=====Row button clicked=====");
    }

    private class OnItemCLickListener implements View.OnClickListener{

        private int mposition;
        public OnItemCLickListener(int position){
            mposition = position;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(activity.getApplicationContext() , "List selected at:"+mposition , Toast.LENGTH_LONG).show();
        }
    }
}
