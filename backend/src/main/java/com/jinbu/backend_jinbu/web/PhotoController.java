package com.jinbu.backend_jinbu.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jinbu.backend_jinbu.entities.Photo;
import com.jinbu.backend_jinbu.service.photo.PhotoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/photo")
public class PhotoController {

    PhotoService photoService;

    @GetMapping("{id}")
    public ResponseEntity<Photo> getPhoto(@PathVariable Long id) {
        return new ResponseEntity<>(photoService.getPhoto(id), HttpStatus.OK);
    }

    // Encontrar la manera de procesar las fotos que los usuarios meten en binario
    @PostMapping
    public ResponseEntity<Photo> savePhoto(@RequestBody Photo photo) {
        return new ResponseEntity<>(photoService.savePhoto(photo), HttpStatus.CREATED);
    }
}
