package com.jinbu.jinbu.web;

import com.jinbu.jinbu.entities.Photo;
import com.jinbu.jinbu.service.ImageService.ImageServiceImplementation;
import com.jinbu.jinbu.service.ImageService.LocalImageStorage;
import com.jinbu.jinbu.service.ImageService.S3ImageStorage;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/images")
public class ImageController {

    ImageServiceImplementation imageServiceImplementation;

    @PostMapping("/upload")
    public ResponseEntity<HttpStatus> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        imageServiceImplementation.store(file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Temporal/Testing, no podemos pedir all en la aplicacion final
    @GetMapping("/all")
    public ResponseEntity<List<Photo>> getAllImages() {
        return new ResponseEntity<>(imageServiceImplementation.retrieveAllImages(), HttpStatus.OK);
    }

}
