package com.example.monsecondwebservice.controleur;

import com.example.monsecondwebservice.dtos.VideoDTO;
import com.example.monsecondwebservice.exceptions.InformationManquanteException;
import com.example.monsecondwebservice.exceptions.LoginDejaPrisException;
import com.example.monsecondwebservice.exceptions.PasDeVideoPourUserException;
import com.example.monsecondwebservice.exceptions.UserInconnuException;
import com.example.monsecondwebservice.model.FacadeImpl;
import com.example.monsecondwebservice.model.User;
import com.example.monsecondwebservice.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;     
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping(value = "/medias", produces = MediaType.APPLICATION_JSON_VALUE)
public class ControleurRest {

    @Autowired
    private FacadeImpl facade = FacadeImpl.getInstance();

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestParam String nom,
                                          @RequestParam String password,
                                          UriComponentsBuilder base) {

        try{
            User user = facade.inscription(nom, password);
            URI location = base.path("/medias/user/{idUser}")
                    .buildAndExpand(user.getIdUser())
                    .toUri();
            return ResponseEntity.created(location).body(user) ;

        } catch (InformationManquanteException e) {
            return ResponseEntity.badRequest().build();
        } catch (LoginDejaPrisException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<User> getUser( @PathVariable String idUser) {
        try {
            return ResponseEntity.ok(facade.getUser(idUser));
        } catch (UserInconnuException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/user")
    public ResponseEntity<Collection<User>> getUsers() {
        return ResponseEntity.ok(facade.getUsers());
    }

    @PostMapping("/user/{idUser}/video")
    public ResponseEntity<Video> saveVideo(@PathVariable String idUser,
                                           @RequestBody VideoDTO videoDTO,
                                           UriComponentsBuilder base) {
        try {
            Video video = facade.saveVideo(idUser,videoDTO.getUrl(), videoDTO.getDescription(), videoDTO.getTitre());
            URI location = base.path("medias/user/{idUser}/video/{idVideo}")
                    .buildAndExpand(idUser, video.getIdVideo())
                    .toUri();

            return ResponseEntity.created(location).body(video);
        } catch (InformationManquanteException e) {
            return  ResponseEntity.badRequest().build();
        } catch (UserInconnuException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/user/{idUser}/video")
    public ResponseEntity<List<Video>> getVideos(@PathVariable String idUser) {

        try {
            return ResponseEntity.ok(facade.getVideos(idUser));
        } catch (PasDeVideoPourUserException e) {
            return (ResponseEntity<List<Video>>) ResponseEntity.ok();
        } catch (UserInconnuException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }



}
