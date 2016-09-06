package com.caragiz_studioz.boombox.dataObjects;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caragiz on 22-08-2016.
 */
public class AlbumData {
    private String albumName;
    private String albumArtPath;
    private Drawable albumArt;
    private List<TrackData> trackData = new ArrayList<>();
    static List<String> albums = new ArrayList<>();

    public AlbumData(String albumName , TrackData trackData){
        this.albumName = albumName;
        this.trackData.add(trackData);
        getMediaInfo();
        albumArt = Drawable.createFromPath(albumArtPath);
    }

    public void addTrackData(TrackData trackData){
        this.trackData.add(trackData);
    }

    public String getAlbumName() {
        return albumName;
    }

    public Drawable getAlbumArt() {
        if (albumArtPath == null)
            return null;

        return albumArt;
    }

    public List<TrackData> getTrackData() {
        return trackData;
    }

    public void getMediaInfo() {
        ContentResolver musicResolver = GlobalResource.contentResolver;
        Uri musicUri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);

        if (musicCursor != null && musicCursor.moveToFirst()) {
            int artCol = musicCursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART);
            int albumCol = musicCursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM);
            //int image = musicCursor.getColumnIndex(MediaStore.Audio.Albums.Al)
            Log.i("Album Art Col: ", String.valueOf(artCol));
            Log.i("Album Col: ", String.valueOf(albumCol));
            do {
                if (musicCursor.getString(albumCol).equals(albumName) && musicCursor.getString(artCol) != null) {
                    albumArtPath = musicCursor.getString(artCol);
                    Log.i("Album Art : ", albumArtPath);
                }
            } while (musicCursor.moveToNext());


        }
    }


}
