package com.jinbu.jinbu.service.ImageService;

import com.jinbu.jinbu.entities.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ImageService {

    Optional<Photo> store(Photo photo, MultipartFile file) throws IOException;

    String retrieveImageUrl(Long id);

    Photo retrieveLocalMetadata(MultipartFile photo);

    List<Photo> retrieveAllImages();

    Page<Photo> retrievePhotosPageable(Pageable pageable);

    void deleteImageById(Long id);

    Page<Photo> getFilteredPhotosSingleValue(
            Integer iso,
            Double aperture,
            Double exposure,
            Pageable pageable
    );

    Page<Photo> getFilteredPhotosBetweenValue(
            Integer isoMin, Integer isoMax,
            Double apertureMin, Double apertureMax,
            Double exposureMin, Double exposureMax,
            Pageable pageable
    );
}
