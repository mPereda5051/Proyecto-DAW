package com.jinbu.jinbu.service.ImageService;

import com.jinbu.jinbu.entities.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {

    void store(MultipartFile file) throws IOException;

    String retrieveImageUrl(Long id);

    List<Photo> retrieveAllImages();

    Page<Photo> retrievePhotosPageable(int pageNumber);

    void deleteImageById(Long id);

    Page<Photo> getFilteredPhotos(
            String name,
            String iso,
            String aperture,
            String exposure,
            Pageable pageable
    );
}
