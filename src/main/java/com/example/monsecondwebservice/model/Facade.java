package com.example.monsecondwebservice.model;

import com.example.monsecondwebservice.dtos.VideoDTO;
import com.example.monsecondwebservice.exceptions.*;

import java.util.Collection;
import java.util.List;

public interface Facade {

    User inscription(String nom , String password) throws
            LoginDejaPrisException, InformationManquanteException;
    User getUser(String idUser) throws UserInconnuException;
    Collection<User> getUsers();

    Video saveVideo(String idUser, String url, String description, String titre) throws UserInconnuException, InformationManquanteException;

    Playlist savePlaylist(String idUser, List<String> idVideos) throws UserInconnuException;

    List<Video> getVideos(String idUser) throws UserInconnuException, PasDeVideoPourUserException;

    List<Playlist> getPlaylist(String idUser) throws UserInconnuException, PasDePlaylistPourUserException;

    void modifyPlaylist(String idUser, Video video) throws UserInconnuException;

    boolean deleteVideoFromPlaylist (String idVideo) throws  VideoInconuException;











}
