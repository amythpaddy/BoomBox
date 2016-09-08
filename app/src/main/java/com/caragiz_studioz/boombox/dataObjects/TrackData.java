package com.caragiz_studioz.boombox.dataObjects;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

public class TrackData{
    private String title;
    private long id;
    private String artist;
    private String album;
    private String path;
    private Drawable albumArt;

    private List<String> albumList = new ArrayList<>();
    private List<String> artistList = new ArrayList<>();

    public TrackData(long id , String title , String artist , String album , String path ){
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.path = path;
        //this.image = image;

        if(!albumList.contains(album)) {
            albumList.add(album);

        }

        if(!artistList.contains(artist))
            artistList.add(artist);
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getPath() { return path; }

    public void setAlbumArt(Drawable albumArt) {
        this.albumArt = albumArt;
    }

    public Drawable getAlbumArt() {
        return albumArt;
    }

    public List<String> getAlbumList() {
        return albumList;
    }

    public List<String> getArtistList() {
        return artistList;
    }
}
