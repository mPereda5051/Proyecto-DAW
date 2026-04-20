package com.jinbu.jinbu.service;

import com.jinbu.jinbu.DTOs.PostDTO;
import com.jinbu.jinbu.entities.Photo;
import com.jinbu.jinbu.entities.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {

    PostDTO getPost(Long id);
    PostDTO savePost(Post post);
    void deletePost(Long id);
    List<PostDTO> getPosts();
    List<PostDTO> getPostsByUserId(Long userId);
    void createPostWithPhoto(Post post, Photo photo);
    Photo retrievePostWithPhoto(Long id);
    Page<Photo> retrievePostWithPhotoPagination(int pageNumber);
}
