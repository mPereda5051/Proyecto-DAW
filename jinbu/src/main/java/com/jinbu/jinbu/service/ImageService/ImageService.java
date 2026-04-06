package com.jinbu.jinbu.service.ImageService;

import com.jinbu.jinbu.entities.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {

    void store(MultipartFile file) throws IOException;

    String retrieveImageUrl(Long id);

    List<Photo> retrieveAllImages();

    void deleteImageById(Long id);
}
