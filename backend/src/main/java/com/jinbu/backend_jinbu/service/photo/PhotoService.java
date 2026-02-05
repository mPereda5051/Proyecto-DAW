package com.jinbu.backend_jinbu.service.photo;

import java.util.List;

import com.jinbu.backend_jinbu.entities.Photo;

public interface PhotoService {
    Photo getPhoto(Long id);
    Photo savePhoto(Photo photo);
    void deletePhoto(Long id);
    List<Photo> getPhotos();
}
