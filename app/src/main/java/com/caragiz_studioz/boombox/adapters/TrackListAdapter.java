package com.caragiz_studioz.boombox.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.caragiz_studioz.boombox.R;
import com.caragiz_studioz.boombox.dataObjects.GlobalResource;
import com.caragiz_studioz.boombox.dataObjects.SongInfo;
import com.caragiz_studioz.boombox.dataObjects.TrackData;

import java.util.List;

/**
 * Created by caragiz on 09-08-2016.
 */
public class TrackListAdapter extends BaseAdapter implements DialogInterface.OnClickListener{
    private Activity activity;
    private static LayoutInflater inflater = null;
    //public Resources resources;
    private List<TrackData> trackDataList;

    TrackData tempValues = null;
    int i = 0;

    public TrackListAdapter(Activity activity ){
        this.activity = activity;
        //this.resources = resources;
        this.trackDataList = SongInfo.albumInfo.get(GlobalResource.albumCardPosition).getTrackData();

        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        Log.i("No of Tracks:",String.valueOf(trackDataList.size()));
        if(trackDataList.size()<=0)
            return 1;
        return trackDataList.size();
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
        public TextView trackName;
        public ImageButton options;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if(convertView == null) {
            view = inflater.inflate(R.layout.track_list, null);
            holder = new ViewHolder();
            holder.options= (ImageButton) view.findViewById(R.id.trackOptions);
            holder.trackName = (TextView) view.findViewById(R.id.trackName);

            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        if(SongInfo.trackInfo.size()<0){
            holder.trackName.setText("No Data");
        }else{
            tempValues = null;
            tempValues = trackDataList.get(position);
            holder.trackName.setText(tempValues.getTitle());

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
