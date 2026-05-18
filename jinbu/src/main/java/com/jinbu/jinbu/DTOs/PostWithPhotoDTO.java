package com.jinbu.jinbu.DTOs;

import com.jinbu.jinbu.entities.Photo;
import com.jinbu.jinbu.entities.Post;

public record PostWithPhotoDTO(
        Photo photo,
        Post post
) {

}
