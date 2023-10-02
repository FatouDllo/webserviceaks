package com.example.monsecondwebservice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private String idUser;
    private String nom;
    private String password;

    private List<Playlist> playlists;
    private List<Video> videos;



    public User(String nom, String password) {
        this.idUser = UUID.randomUUID().toString();
        this.nom = nom;
        this.password = password;
        this.playlists = new ArrayList<>();
        this.videos = new ArrayList<>();
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

    public void addPlaylist(Playlist playlist) {
        this.playlists.add(playlist);
    }

    public void deletePlaylist(String idPlaylist) {
        for (Playlist v : playlists) {
            if( v.getIdPlaylist().equals(idPlaylist)){
                playlists.remove(idPlaylist);
            }
        }
    }

    public User() {
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
