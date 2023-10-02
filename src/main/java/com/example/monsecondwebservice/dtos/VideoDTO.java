package com.example.monsecondwebservice.dtos;

public class VideoDTO{
    private String url;
    private String description;
    private String titre;

    public VideoDTO(String url, String description, String titre) {
        this.url = url;
        this.description = description;
        this.titre = titre;
    }

    public VideoDTO() {
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
