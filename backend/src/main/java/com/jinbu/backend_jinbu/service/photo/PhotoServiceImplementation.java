package com.jinbu.backend_jinbu.service.photo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jinbu.backend_jinbu.entities.Photo;
import com.jinbu.backend_jinbu.exception.PhotoNotFoundException;
import com.jinbu.backend_jinbu.repository.PhotoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PhotoServiceImplementation implements PhotoService{
    
    PhotoRepository photoRepository;

    @Override
    public Photo getPhoto(Long id) {
        Optional<Photo> user = photoRepository.findById(id);
        return unwrapUser(user, id);
    }

    @Override
    public Photo savePhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public void deletePhoto(Long id) {
        photoRepository.deleteById(id);
    }

    @Override
    public List<Photo> getPhotos() {
        return (List<Photo>)photoRepository.findAll();
    }

    // Lanzamos error si la foto no existe, sino devolvemos foto
    static Photo unwrapUser(Optional<Photo> entity, long id) {
        if (entity.isPresent()) return entity.get();
        else throw new PhotoNotFoundException(id);
    }
}
