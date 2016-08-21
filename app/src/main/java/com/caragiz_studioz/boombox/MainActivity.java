package com.caragiz_studioz.boombox;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.caragiz_studioz.boombox.adapters.RecycleViewAdapter;
import com.caragiz_studioz.boombox.adapters.TrackListAdapter;
import com.caragiz_studioz.boombox.dataObjects.SongInfo;
import com.caragiz_studioz.boombox.dataObjects.TrackData;
import com.caragiz_studioz.boombox.helper.DbHelper;
import com.caragiz_studioz.boombox.helper.PlayHelper;
import com.caragiz_studioz.boombox.services.PlayerService;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    ViewPager mainPage;
    Button start;
    Button stop;
    TextView outputTxt;
    ListView titleListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymainrecycler);
        int permissionChk = ContextCompat.checkSelfPermission(this , Manifest.permission.READ_EXTERNAL_STORAGE);
        if(permissionChk != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE} , 1);
            getTrackList();
        }
        else
            getTrackList();

//********************************Setting Card Layout *********************************//

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.cardList);
        recyclerView.hasFixedSize();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new RecycleViewAdapter());

//********************************Setting Card Layout *********************************//

/*        registerReceiver(updateText , new IntentFilter("updateText"));

        start = (Button)findViewById(com.caragiz_studioz.boombox.R.id.startBtn);
        stop = (Button)findViewById(R.
                id.stopBtn);
        //outputTxt = (TextView)findViewById(R.id.outputTxt);

        int permissionChk = ContextCompat.checkSelfPermission(this , Manifest.permission.READ_EXTERNAL_STORAGE);
        if(permissionChk != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE} , 1);
            getTrackList();
        }
        else
            getTrackList();

        titleListView = (ListView)findViewById(R.id.titleListView);
        Resources resources = getResources();
        TrackListAdapter trackListAdapter = new TrackListAdapter(this , resources);
        titleListView.setAdapter(trackListAdapter);


        start.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent serviceIntent = new Intent(getBaseContext() , PlayerService.class);
                //Log.i("Sending",value.getText().toString());
                //serviceIntent.putExtra("init" , value.getText().toString());
                startService(serviceIntent);
            }
        });
    }

    private BroadcastReceiver updateText = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
           // outputTxt.setText(outputTxt.getText() + " :: " + intent.getExtras().getString("count"));
        }
    };

    public void stopService(View view){
        Log.i("Check Status" , "In Stop Service");

        getTrackList();
        PlayHelper.pause();
        stopService(new Intent(getBaseContext() , PlayerService.class));
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        unregisterReceiver(updateText);

*/    }

    public void getTrackList(){
        ContentResolver contentResolver = getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        DbHelper.getMediaInfo(contentResolver , musicUri);
        String names = null;
        Iterator<TrackData> it = SongInfo.trackInfo.iterator();
        while(it.hasNext()){
            TrackData td = it.next();
            names = names + td.getTitle() + "\n";
        }

    }
}
