package com.jinbu.jinbu.repository;

import com.jinbu.jinbu.entities.Photo;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long>, JpaSpecificationExecutor<Photo> {
//    Page<Photo> getPhotoByExposureAfter(String exposureAfter, Pageable pageable);
//    Page<Photo> getPhotoByApertureAfter(String apertureAfter, Pageable pageable);
//    Page<Photo> getPhotoByDateAfter(Date dateAfter, Pageable pageable);
//    List<Photo> getPhotoByIsoBefore(String isoBefore);
//    Page<Photo> getPhotoByWidthBetween(String widthAfter, String widthBefore, Pageable pageable);
//    Page<Photo> getPhotoByHeightBetween(String heightAfter, String heightBefore, Pageable pageable);
//
//    Page<Photo> getPhotoByExposureGreaterThan(String exposureIsGreaterThan, Pageable pageable);
//
//    Pageable sortedByDate =
//            PageRequest.of(0, 5, Sort.by("Aperture").descending());

}
