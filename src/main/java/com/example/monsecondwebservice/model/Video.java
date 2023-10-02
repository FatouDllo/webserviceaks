package com.example.monsecondwebservice.model;

import java.util.UUID;

public class Video {

    private String idVideo;
    private String url;
    private String description;
    private String titre;

    public Video(String url, String description, String titre) {
        this.idVideo = UUID.randomUUID().toString();
        this.url = url;
        this.description = description;
        this.titre = titre;
    }

    public Video() {
    }


    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
