package com.caragiz_studioz.boombox.helper;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import com.caragiz_studioz.boombox.dataObjects.SongInfo;
import com.caragiz_studioz.boombox.dataObjects.TrackData;

import java.io.IOException;
import java.util.Random;

/**
 * Created by caragiz on 08-08-2016.
 */
public class PlayHelper {
    static Context context;
    static MediaPlayer mPlayer;

    public static void play(Context context) {
        PlayHelper.context = context;
        //AudioManager player = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        mPlayer = new MediaPlayer();
        Random rand = new Random();
        int random = rand.nextInt(SongInfo.trackInfo.size())+1;
        Log.i("Random" , random+"");
        TrackData trackData = SongInfo.trackInfo.get(random);
        try {
            mPlayer.setDataSource(trackData.getPath());
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void pause(){
        mPlayer.pause();
    }
}
