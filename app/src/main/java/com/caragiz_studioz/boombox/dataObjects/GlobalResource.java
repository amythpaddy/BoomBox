package com.caragiz_studioz.boombox.dataObjects;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.media.Image;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.caragiz_studioz.boombox.MainActivity;
import com.caragiz_studioz.boombox.services.PlayerService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caragiz on 24-08-2016.
 */
public class GlobalResource {
    public static int albumCardPosition;
    public static boolean isPlaying = false;
    public static boolean repeat;
    public static boolean shuffle;
    public static ContentResolver contentResolver;
    public static int pauseSeekPosition;
    private static int playListPosition = 0;
    private static List<TrackData> playList = new ArrayList<>();
    public static boolean isPaused;
    private static Activity activity;

    public static TrackData getCurrentSong() {
        TrackData tempTrackData = playList.get(playListPosition);
        tempTrackData.setAlbumArt(SongInfo.albumInfo.get(GlobalResource.albumCardPosition).getAlbumArt());
        return tempTrackData;
    }

    public static void currentSongCompleted() {
        playListPosition++;
        isPaused = false;
        pauseSeekPosition = 0;
    }

    public static void onPause(int position) {
        isPaused = true;
        pauseSeekPosition = position;

    }

    public static void setCurrentSong(TrackData trackData) {
        playList.add(trackData);
        if(!GlobalResource.isPlaying && !GlobalResource.isPaused){
            activity.startService(new Intent(activity, PlayerService.class));
        }
    }

    public static boolean isListOver() {
        if (playList.size() == playListPosition)
            return true;
        return false;
    }


    public static void setActivity(Activity activity) {
        GlobalResource.activity = activity;
    }
}
