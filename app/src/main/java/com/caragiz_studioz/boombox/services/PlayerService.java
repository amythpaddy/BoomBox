package com.caragiz_studioz.boombox.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.caragiz_studioz.boombox.helper.PlayHelper;

/**
 * Created by caragiz on 23-07-2016.
 */
public class PlayerService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {
    x
    Intent i = new Intent("updateText");
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        i.putExtra("count" , "Destroyed");
        sendBroadcast(i);
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent , int flags , int startId){

        i.putExtra("count" , "Start");

        sendBroadcast(i);
        //Log.i("Found" , intent.getExtras().getString("init"));
        Toast.makeText(this, "Welcome " , Toast.LENGTH_LONG).show();
        PlayHelper.play(getApplicationContext());
        return super.onStartCommand(intent , flags , startId);
    }


    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }
}
