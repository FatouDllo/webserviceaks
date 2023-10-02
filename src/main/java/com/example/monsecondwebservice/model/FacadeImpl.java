package com.example.monsecondwebservice.model;

import com.example.monsecondwebservice.exceptions.*;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class FacadeImpl implements Facade{

    Map<String,User>  userInscrits;
    private Map<String, List<Video>> videos;
    private Map<String, List<Playlist>> playlists;
    private List<Video> toutesVideos;


    public static FacadeImpl getInstance() {
        return new FacadeImpl();
    }
    public FacadeImpl() {
        this.userInscrits = new HashMap<>();
        this.videos = new HashMap<>();
        this.playlists = new HashMap<>();
        this.toutesVideos = new ArrayList<>();
    }

    @Override
    public User inscription(String nom, String password) throws
            LoginDejaPrisException, InformationManquanteException {
        if (nom == null ||  password == null ||
                "".equals(nom) ||  "".equals(password)) {
            throw new InformationManquanteException();
        }
        User user = userInscrits.get(nom);
        if (user != null) {
            throw new LoginDejaPrisException();
        }
        user = new User(nom, password);
        this.userInscrits.put(user.getIdUser(),user);
        return  user;
    }

    @Override
    public User getUser(String idUser) throws UserInconnuException {
        User user;
        if (userInscrits.containsKey(idUser)){
            user = userInscrits.get(idUser);
        }else {
            throw new UserInconnuException();
        }
        return user;
    }

    @Override
    public Collection<User> getUsers() {
        return userInscrits.values();
    }

    @Override
    public Video saveVideo(String idUser, String url, String description, String titre) throws UserInconnuException, InformationManquanteException {
        if (!userInscrits.containsKey(idUser)){
            throw new UserInconnuException();
        }
        if (url == null || titre == null ||
                "".equals(url) || "".equals(titre)) {
            throw new InformationManquanteException();
        }
        Video video = new Video(url,description, titre);
        this.toutesVideos.add(video);
        if(videos.containsKey(idUser)){
            videos.get(idUser).add(video);
        }else{
            List<Video> mesVideo = new ArrayList<>();
            mesVideo.add(video);
            this.videos.put(idUser,mesVideo);
        }
        return video;
    }

    @Override
    public Playlist savePlaylist(String idUser, List<String>  idVideos) throws UserInconnuException {
        if (!userInscrits.containsKey(idUser))
            throw new UserInconnuException();
        Playlist playlist = new Playlist();
        for (String idV : idVideos){
            Video video = new Video();
            video.setIdVideo(idV);
            if(toutesVideos.stream().map(v -> v.getIdVideo()).equals(video.getIdVideo())){
                //Video video1 = toutesVideos.stream().forEach(v -> (v.getIdVideo().equals(video.getIdVideo()))).;
                //playlist.addVideo(video1);
            }
        }
        if(playlists.containsKey(idUser)){
            playlists.get(idUser).add(playlist);
        }else{
            List<Playlist> mesPlaylist = new ArrayList<>();
            mesPlaylist.add(playlist);
            this.playlists.put(idUser,mesPlaylist);
        }
        return playlist;
    }


    @Override
    public List<Video> getVideos(String idUser) throws UserInconnuException, PasDeVideoPourUserException {
        if (!userInscrits.containsKey(idUser))
            throw new UserInconnuException();
        if(!videos.containsKey(idUser))
            throw new PasDeVideoPourUserException();
        return  videos.get(idUser);
    }

    @Override
    public List<Playlist> getPlaylist(String idUser) throws UserInconnuException, PasDePlaylistPourUserException {
        if (!userInscrits.containsKey(idUser))
            throw new UserInconnuException();
        if(!videos.containsKey(idUser))
            throw new PasDePlaylistPourUserException();
        return playlists.get(idUser);
    }

    @Override
    public void modifyPlaylist(String idUser, Video video) throws UserInconnuException {
        if (!userInscrits.containsKey(idUser))
            throw new UserInconnuException();
    }

    @Override
    public boolean deleteVideoFromPlaylist(String idVideo) throws VideoInconuException {
        return false;
    }
}
