package com.caragiz_studioz.boombox;

import android.app.Activity;
import android.app.ActivityOptions;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.caragiz_studioz.boombox.adapters.TrackListAdapter;
import com.caragiz_studioz.boombox.dataObjects.AlbumData;
import com.caragiz_studioz.boombox.dataObjects.GlobalResource;
import com.caragiz_studioz.boombox.dataObjects.SongInfo;

/**
 * Created by caragiz on 24-08-2016.
 */
public class DetailedAlbumActivity extends Activity {

    ImageView albumArt;
    TextView albumName;
    ListView trackListView;

    static int minVisibleItem = 10000;
    static int prevFirstVisibleItem = 0;
    static boolean shrinked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.detailedcardview);

        albumArt = (ImageView)findViewById(R.id.albumArt);
        albumName = (TextView) findViewById(R.id.albumName);
        trackListView = (ListView)findViewById(R.id.tracksListView);
        albumName.setText(SongInfo.albumInfo.get(GlobalResource.albumCardPosition).getAlbumName());

        trackListView.setAdapter(new TrackListAdapter(this ));
        trackListView.setOnScrollListener(new ScrollListener());
    }

    class ScrollListener implements AbsListView.OnScrollListener{
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            minVisibleItem = minVisibleItem > visibleItemCount ? visibleItemCount : minVisibleItem;
            Log.i("Prev First Visible Item" , ":"+prevFirstVisibleItem);
            Log.i("Shrinked" , ":"+shrinked);
            if((firstVisibleItem < prevFirstVisibleItem && shrinked) || (firstVisibleItem == 0 && shrinked)){
                shrinked = false;
                int width = albumArt.getWidth();
                int height = 600;
                RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(width,height);
                albumArt.setLayoutParams(parms);
            }
            else if(minVisibleItem != visibleItemCount && firstVisibleItem != 0 && !shrinked){
                shrinked = true;
                Log.i("Height Change:","true");
                int width = albumArt.getWidth();
                int height = 200;
                RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(width,height);
                albumArt.setLayoutParams(parms);
            }
            prevFirstVisibleItem = firstVisibleItem;



            Log.i("First Visible Item" , ":"+firstVisibleItem);
            Log.i("Visible Item Count" , ":"+visibleItemCount);
            Log.i("Total Item Counte" , ":"+totalItemCount);
        }
    }
}

