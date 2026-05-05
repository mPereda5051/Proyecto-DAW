package com.jinbu.jinbu.service;

import com.jinbu.jinbu.DTOs.PostDTO;
import com.jinbu.jinbu.entities.Photo;
import com.jinbu.jinbu.entities.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {

    PostDTO getPost(Long id);
    void deletePost(Long id);
    List<PostDTO> getPostsByUserId(Long userId);
    void createPost(Post post, Photo photo);
    List<PostDTO> retrievePosts(int pageNumber);
}
