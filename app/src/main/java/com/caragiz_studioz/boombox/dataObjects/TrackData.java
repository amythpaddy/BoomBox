package com.caragiz_studioz.boombox.dataObjects;

public class TrackData{
    private String title;
    private long id;
    private String artist;
    private String album;
    private String path;

    public TrackData(long id , String title , String artist , String album , String path){
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.path = path;
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
}
