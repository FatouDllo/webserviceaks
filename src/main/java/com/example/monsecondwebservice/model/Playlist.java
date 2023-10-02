package com.example.monsecondwebservice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Playlist {
    private String idPlaylist;
    private List<Video> videos;

    public Playlist() {
        this.idPlaylist = UUID.randomUUID().toString() ;
        this.videos = new ArrayList<>();
    }

    public Playlist(List<Video> videos) {
        this.idPlaylist = UUID.randomUUID().toString() ;
        this.videos = videos;
    }

    public void addVideo(Video video) {
        this.videos.add(video);
    }

    public void deleteVideo(String idVideo) {
        for (Video v : videos) {
            if( v.getIdVideo().equals(idVideo)){
                videos.remove(idVideo);
            }
        }
    }
    public String getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(String idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
