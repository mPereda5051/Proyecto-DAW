package com.jinbu.jinbu.service.ImageService;

import com.jinbu.jinbu.entities.Photo;
import com.jinbu.jinbu.exceptions.EntityNotFoundException;
import com.jinbu.jinbu.repository.PhotoRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ImageServiceImplementation implements ImageService{

    LocalImageStorage localImageStorage;
    S3ImageStorage s3ImageStorage;
    PhotoRepository photoRepository;

    @Transactional
    @Override
    public Optional<Photo> store(Photo photo, MultipartFile file) throws IOException {
        Long photoId = localImageStorage.storeMetadata(photo);

        // Anadir exceptions personalizados
        try {
            s3ImageStorage.store(file, photoId);
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException("Upload failed", e);
        }

        return photoRepository.findById(photoId);
    }

    @Override
    public String retrieveImageUrl(Long id) {
        Photo photo = photoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Photo.class));

        return photo.getFullUrl();
    }

    @Override
    public Photo retrieveLocalMetadata(MultipartFile photo) {
        try {
            return localImageStorage.retrieveMetadata(photo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Photo> retrieveAllImages() {
        return photoRepository.findAll();
    }

    @Override
    public Page<Photo> retrievePhotosPageable(Pageable pageable ) {
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
    public Page<Photo> getFilteredPhotosSingleValue(
            Integer iso,
            Double aperture,
            Double exposure,
            Pageable pageable) {

        return photoRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (iso != null) {
                predicates.add(cb.equal(root.get("iso"), iso));
            }

            if (aperture != null) {
                predicates.add(cb.equal(root.get("aperture"), aperture));
            }

            if (exposure != null) {
                predicates.add(cb.greaterThan(root.get("exposure"), exposure));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }

    @Override
    public Page<Photo> getFilteredPhotosBetweenValue(
            Integer isoMin, Integer isoMax,
            Double apertureMin, Double apertureMax,
            Double exposureMin, Double exposureMax,
            Pageable pageable) {

        return photoRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();


            if (isoMin != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("iso"), isoMin));
            }
            if (isoMax != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("iso"), isoMax));
            }

            if (apertureMin != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("aperture"), apertureMin));
            }
            if (apertureMax != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("aperture"), apertureMax));
            }

            if (exposureMin != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("exposure"), exposureMin));
            }
            if (exposureMax != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("exposure"), exposureMax));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }


}
