package com.caragiz_studioz.boombox.helper;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.caragiz_studioz.boombox.dataObjects.SongInfo;

/**
 * Created by caragiz on 25-07-2016.
 */
public class DbHelper {

    public static void getMediaInfo(ContentResolver musicResolver , Uri musicUri) {
        Cursor musicCursor = musicResolver.query(musicUri,null,null,null,null);

        if(musicCursor != null && musicCursor.moveToFirst()){
            int idCol = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int titleCol = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int artistCol = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int albumCol = musicCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
            int pathCol = musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            //int image = musicCursor.getColumnIndex(MediaStore.Images.Thumbnails.);

            do {
                SongInfo.addTrackInfo(musicCursor.getLong(idCol) , musicCursor.getString(titleCol) , musicCursor.getString(artistCol) , musicCursor.getString(albumCol) , musicCursor.getString(pathCol));
            }while(musicCursor.moveToNext());


            musicCursor.close();
        }
    }
}
