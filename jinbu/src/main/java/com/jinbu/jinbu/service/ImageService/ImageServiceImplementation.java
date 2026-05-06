package com.jinbu.jinbu.service.ImageService;

import com.jinbu.jinbu.DTOs.PostDTO;
import com.jinbu.jinbu.constants.AppConstants;
import com.jinbu.jinbu.entities.Photo;
import com.jinbu.jinbu.exceptions.EntityNotFoundException;
import com.jinbu.jinbu.repository.PhotoRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Photo> retrieveAllImages() {
        return photoRepository.findAll();
    }

    @Override
    public Page<Photo> retrievePhotosPageable(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        return photoRepository.findAll(pageable);
    }

    // Revisar si funciona la parte de s3
    @Override
    public void deleteImageById(Long id) {
        Photo photo = photoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Photo.class));

        String extension = photo.getExtension();

        photoRepository.delete(photo);
        s3ImageStorage.delete(id, extension);
    }

    @Override
    public Page<Photo> getFilteredPhotos(
            String iso,
            String aperture,
            String exposure,
            String name,
            Pageable pageable) {

        return photoRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (iso != null && !iso.isEmpty()) {
                predicates.add(cb.equal(root.get("iso"), iso));
            }

            if (aperture != null && !aperture.isEmpty()) {
                predicates.add(cb.equal(root.get("aperture"), aperture));
            }

            if (exposure != null && !exposure.isEmpty()) {
                predicates.add(cb.greaterThan(root.get("exposure"), exposure));
            }

            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }


}
