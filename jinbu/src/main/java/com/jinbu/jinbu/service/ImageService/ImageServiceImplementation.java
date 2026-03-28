package com.jinbu.jinbu.service.ImageService;

import com.jinbu.jinbu.constants.AppConstants;
import com.jinbu.jinbu.entities.Photo;
import com.jinbu.jinbu.exceptions.EntityNotFoundException;
import com.jinbu.jinbu.repository.PhotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@Service
public class ImageServiceImplementation implements ImageService{

    LocalImageStorage localImageStorage;
    S3ImageStorage s3ImageStorage;
    PhotoRepository photoRepository;

    @Transactional
    @Override
    public void store(MultipartFile file) throws IOException {
        Long photoId = localImageStorage.storeMetadata(file);

        // Anadir exceptions personalizados
        try {
            s3ImageStorage.store(file, photoId);
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException("Upload failed", e);
        }
    }

    @Override
    public String retrieveImageUrl(Long id) {
        Photo photo = photoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Photo.class));

        return photo.getFullUrl();
    }
}
