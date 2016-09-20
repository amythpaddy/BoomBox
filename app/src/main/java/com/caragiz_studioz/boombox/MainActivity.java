package com.caragiz_studioz.boombox;

import android.Manifest;
import android.app.Activity;
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
import android.support.annotation.NonNull;
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
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.caragiz_studioz.boombox.adapters.RecycleViewAdapter;
import com.caragiz_studioz.boombox.adapters.TrackListAdapter;
import com.caragiz_studioz.boombox.dataObjects.GlobalResource;
import com.caragiz_studioz.boombox.dataObjects.SongInfo;
import com.caragiz_studioz.boombox.dataObjects.TrackData;
import com.caragiz_studioz.boombox.helper.DbHelper;
import com.caragiz_studioz.boombox.helper.PlayHelper;
import com.caragiz_studioz.boombox.services.CardOnClickListenerService;
import com.caragiz_studioz.boombox.services.PlayerService;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{

    ViewPager mainPage;
    Button start;
    Button stop;
    TextView albumName;
    ImageView albumArt;
    ListView titleListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activitymainrecycler);

        albumArt = (ImageView)findViewById(R.id.albumArt);
        albumName = (TextView)findViewById(R.id.albumName);
        GlobalResource.contentResolver = getContentResolver();
        GlobalResource.setActivity(this);
        int permissionChk = ContextCompat.checkSelfPermission(this , Manifest.permission.READ_EXTERNAL_STORAGE);
        if(!checkPermission()){
               requestPermission();
        }else{
            getTrackList();
            RecyclerView recyclerView = (RecyclerView)findViewById(R.id.cardList);
            recyclerView.hasFixedSize();
            /*LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            */
            StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(new RecycleViewAdapter());
            recyclerView.addOnItemTouchListener(new CardOnClickListenerService(this));

        }


//********************************Setting Card Layout *********************************//


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



    @Override
    public void  onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.i("GOt response code :","Adding Adapter");
        if(requestCode == 1){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getTrackList();
                RecyclerView recyclerView = (RecyclerView)findViewById(R.id.cardList);
                recyclerView.hasFixedSize();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(new RecycleViewAdapter());
                recyclerView.addOnItemTouchListener(new CardOnClickListenerService(this));

            }
        }
    }

    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED){

            return true;

        } else {

            return false;

        }
    }

    private void requestPermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){

            Toast.makeText(this,"GPS permission allows us to access location data. Please allow in App Settings for additional functionality.",Toast.LENGTH_LONG).show();

        } else {
            Log.i("Getting permission :","Read External Storage");
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }
    }

  /*  @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this,"Permission Granted, Reading Data.",Toast.LENGTH_LONG).show();
                    getTrackList();
                    RecyclerView recyclerView = (RecyclerView)findViewById(R.id.cardList);
                    recyclerView.hasFixedSize();
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(new RecycleViewAdapter());

                } else {

                    Toast.makeText(this,"Permission Denied, You cannot access location data.",Toast.LENGTH_LONG).show();

                }
                break;
        }
    }*/
}
