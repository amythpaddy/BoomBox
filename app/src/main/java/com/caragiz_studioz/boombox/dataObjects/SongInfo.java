package com.caragiz_studioz.boombox.dataObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caragiz on 25-07-2016.
 */
public class SongInfo {
    public static List<TrackData> trackInfo = new ArrayList<>();
    public static void addTrackInfo(long id , String title , String artist , String album , String path){
        TrackData trackData = new TrackData(id , title , artist , album , path);
        trackInfo.add(trackData);

    }
}

