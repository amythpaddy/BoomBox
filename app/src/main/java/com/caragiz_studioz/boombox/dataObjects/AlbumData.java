package com.caragiz_studioz.boombox.dataObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caragiz on 22-08-2016.
 */
public class AlbumData {
    private String albumName;
    private List<TrackData> trackData = new ArrayList<>();
    static List<String> albums = new ArrayList<>();

    public AlbumData(String albumName , TrackData trackData){
        this.albumName = albumName;
        this.trackData.add(trackData);
    }

    public void addTrackData(TrackData trackData){
        this.trackData.add(trackData);
    }

    public String getAlbumName() {
        return albumName;
    }

    public List<TrackData> getTrackData() {
        return trackData;
    }
}
