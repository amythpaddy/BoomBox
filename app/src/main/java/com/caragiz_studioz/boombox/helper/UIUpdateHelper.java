package com.caragiz_studioz.boombox.helper;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.caragiz_studioz.boombox.dataObjects.TrackData;
import com.caragiz_studioz.boombox.services.PlayerService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caragiz on 8/9/16.
 */
public class UIUpdateHelper {
    private static List<TextView> trackNames = new ArrayList<>();
    private static List<TextView> albumNames = new ArrayList<>();
    private static List<TextView> artistNames = new ArrayList<>();
    private static List<ImageView> albumArts = new ArrayList<>();
    private static List<ProgressBar> progressBars = new ArrayList<>();
    private static List<SeekBar> seekBars = new ArrayList<>();
    private static List<RelativeLayout> relativeLayouts = new ArrayList<>();

    private static TrackData tempTrackData;


    public static void updateAllUI(TrackData trackData, Drawable albumArtDrawable) {
        for (TextView trackName : trackNames)
            trackName.setText(trackData.getTitle());

        for (TextView albumName : albumNames)
            albumName.setText(trackData.getAlbum());

        for (TextView artistName : artistNames)
            artistName.setText(trackData.getArtist());

        for (ImageView albumArt : albumArts)
            if (albumArtDrawable != null)
                albumArt.setImageDrawable(albumArtDrawable);

        for (RelativeLayout relativeLayout : relativeLayouts)
            if (albumArtDrawable != null)
                relativeLayout.setBackground(albumArtDrawable);

        tempTrackData = trackData;
        /*for(SeekBar seekBar : seekBars)
            seekBar.setMax(max);*/
    }

    public static void addUpdateListenerFor(ImageView albumArt, TextView trackName, TextView albumName, TextView artistName, ProgressBar progressBar, SeekBar seekBar) {
        if (albumArt != null && !albumArts.contains(albumArt))
            albumArts.add(albumArt);

        if (trackName != null && !trackNames.contains(trackName))
            trackNames.add(trackName);

        if (albumName != null && !albumNames.contains(albumName))
            albumNames.add(albumName);

        if (artistName != null && !artistNames.contains(artistName))
            artistNames.add(artistName);

        if (progressBar != null && !progressBars.contains(progressBar))
            progressBars.add(progressBar);

        if (seekBar != null && !seekBars.contains(seekBar))
            seekBars.add(seekBar);

        Log.i("albumArtListener Size:", ":" + albumArts.size());
        Log.i("albumArtListener Size:", ":" + progressBars.size());
        Log.i("albumArtListener Size:", ":" + seekBars.size());
        if(tempTrackData != null)
        updateAllUI(tempTrackData, tempTrackData.getAlbumArt());
    }

    public static void addUpdateListenerFor(ImageView albumArt, TextView trackName, TextView albumName, TextView artistName, SeekBar seekBar) {
        if (albumArt != null && !albumArts.contains(albumArt))
            albumArts.add(albumArt);

        if (trackName != null && !trackNames.contains(trackName))
            trackNames.add(trackName);

        if (albumName != null && !albumNames.contains(albumName))
            albumNames.add(albumName);

        if (artistName != null && !artistNames.contains(artistName))
            artistNames.add(artistName);

        if (seekBar != null && !seekBars.contains(seekBar))
            seekBars.add(seekBar);

        if(tempTrackData != null)
        updateAllUI(tempTrackData, tempTrackData.getAlbumArt());

        Log.i("LayoutListener Size:", ":" + relativeLayouts.size());
        Log.i("albumArtListener Size:", ":" + progressBars.size());
        Log.i("albumArtListener Size:", ":" + seekBars.size());
    }

    public static void updateProgress(int progress, int max) {
        for (ProgressBar progressBar : progressBars) {
            progressBar.setMax(max);
            progressBar.setProgress(progress);
        }

        for (SeekBar seekBar : seekBars) {
            seekBar.setMax(max);
            seekBar.setProgress(progress);
        }


    }
}
