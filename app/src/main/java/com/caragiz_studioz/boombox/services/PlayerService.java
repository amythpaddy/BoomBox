package com.caragiz_studioz.boombox.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.caragiz_studioz.boombox.dataObjects.GlobalResource;
import com.caragiz_studioz.boombox.dataObjects.TrackData;
import com.caragiz_studioz.boombox.helper.PlayHelper;
import com.caragiz_studioz.boombox.helper.UIUpdateHelper;

/**
 * Created by caragiz on 23-07-2016.
 */
public class PlayerService extends Service implements MediaPlayer.OnCompletionListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {

    private MediaPlayer mediaPlayer = new MediaPlayer();
    private String setAudioLink;
    private TrackData playThis;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnSeekCompleteListener(this);
        mediaPlayer.setOnInfoListener(this);
        mediaPlayer.reset();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Service :", "Starting Playback");

        super.onStartCommand(intent, flags, startId);
        //setAudioLink = intent.getExtras().getString("sentAudioLink");
        initializePlayer();
        return START_STICKY;
    }

    private void initializePlayer() {
        playThis = GlobalResource.getCurrentSong();
        GlobalResource.isPlaying = true;
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(playThis.getPath());
            mediaPlayer.prepareAsync();
            //mediaPlayer.start();
        } catch (Exception e) {

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            stopMedia();
        }
        GlobalResource.isPlaying = false;
        Log.i("Service:", "Playback Paused");
        mediaPlayer.release();
    }


    @Override
    public void onCompletion(MediaPlayer mp) {
        if (GlobalResource.isListOver()) {
            stopMedia();
            stopSelf();
        } else {
            GlobalResource.currentSongCompleted();
            initializePlayer();
        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
                makeToast("Incorrect Media Format");
                break;

            case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                makeToast("Unknown Media Format");
        }
        return false;
    }

    private void makeToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        playMedia();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {

    }

    public void playMedia() {
        if (!mediaPlayer.isPlaying()) {
            UIUpdateHelper.updateAllUI(playThis, playThis.getAlbumArt());
            if (GlobalResource.isPaused) {
                mediaPlayer.seekTo(GlobalResource.pauseSeekPosition);
            }
            mediaPlayer.start();
            new SeekProgress().start();
        }
    }


    public void stopMedia() {
        if (mediaPlayer.isPlaying()) {
            Log.i("Service", "Setting pause at" + mediaPlayer.getCurrentPosition());
            GlobalResource.onPause(mediaPlayer.getCurrentPosition());
            mediaPlayer.stop();
        }
    }

    class SeekProgress extends Thread {

        public void run() {

            while (GlobalResource.isPlaying) {
                UIUpdateHelper.updateProgress(mediaPlayer.getCurrentPosition(), mediaPlayer.getDuration());
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
            }
        }
    }
}
