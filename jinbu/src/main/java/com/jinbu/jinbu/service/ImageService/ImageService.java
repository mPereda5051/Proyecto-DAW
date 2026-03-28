package com.jinbu.jinbu.service.ImageService;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    void store(MultipartFile file) throws IOException;

    String retrieveImageUrl(Long id);
}
