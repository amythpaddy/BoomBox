package com.caragiz_studioz.boombox.dataObjects;

import java.util.ArrayList;

/**
 * Created by caragiz on 22-08-2016.
 */
public class ArtistData {
    private String artistName;
    private ArrayList<TrackData> trackData;

    public ArtistData(String artistName , TrackData trackData){
        this.artistName = artistName;
        this.trackData = new ArrayList<>();
        this.trackData.add(trackData);
    }

    public void addTrackData(TrackData trackData){
        this.trackData.add(trackData);
    }

    public String getArtistName() {
        return artistName;
    }

    public ArrayList<TrackData> getTrackData() {
        return trackData;
    }
}
