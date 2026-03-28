package com.jinbu.jinbu.web;

import com.jinbu.jinbu.service.ImageService.ImageServiceImplementation;
import com.jinbu.jinbu.service.ImageService.LocalImageStorage;
import com.jinbu.jinbu.service.ImageService.S3ImageStorage;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    // Get Mapping para pedir la url
}
