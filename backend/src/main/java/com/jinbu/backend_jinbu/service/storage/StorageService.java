package com.jinbu.backend_jinbu.service.storage;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String store(MultipartFile file) throws IOException;
    Resource loadAsResource(String filename) throws IOException;
    boolean delete(String filename) throws IOException;
}
