package com.caragiz_studioz.boombox.dataObjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by caragiz on 25-07-2016.
 */
public class SongInfo {
    public static List<TrackData> trackInfo = new ArrayList<>();
    public static List<AlbumData> albumInfo = new ArrayList<>();
    public static List<ArtistData> artistInfo = new ArrayList<>();
    static List<String> albumName = new ArrayList<>();
    static List<String> artistName = new ArrayList<>();

    public static void addTrackInfo(long id , String title , String artist , String album , String path){
        TrackData trackData = new TrackData(id , title , artist , album , path);
        trackInfo.add(trackData);
        if(!albumName.contains(album)) {
            AlbumData albumData = new AlbumData(album, trackData);
            albumInfo.add(albumData);
            albumName.add(album);
        }else{
            Iterator<AlbumData> it = albumInfo.iterator();
            while(it.hasNext()){
                AlbumData albumData = it.next();
                if(albumData.getAlbumName().equals(album)){
                    albumData.addTrackData(trackData);
                    break;
                }
            }
        }
        if(!artistName.contains(artist)) {
            ArtistData artistData = new ArtistData(artist, trackData);
            artistInfo.add(artistData);
            artistName.add(artist);
        }else{
            Iterator<ArtistData> it = artistInfo.iterator();
            while(it.hasNext()){
                ArtistData artistData = it.next();
                if(artistData.getArtistName().equals(artist)){
                    artistData.addTrackData(trackData);
                    break;
                }
            }
        }

    }
}

