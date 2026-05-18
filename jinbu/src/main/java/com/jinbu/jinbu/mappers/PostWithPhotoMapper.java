package com.jinbu.jinbu.mappers;

import com.jinbu.jinbu.DTOs.PostWithPhotoDTO;
import com.jinbu.jinbu.entities.Photo;
import com.jinbu.jinbu.entities.Post;

public class PostWithPhotoMapper {

    public PostWithPhotoDTO toDTO(Photo photo, Post post) {
        return new PostWithPhotoDTO(
                photo,
                post
        );
    }
}
