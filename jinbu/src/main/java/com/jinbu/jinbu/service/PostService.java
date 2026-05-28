package com.jinbu.jinbu.service;

import com.jinbu.jinbu.DTOs.PostDTO;
import com.jinbu.jinbu.entities.Photo;
import com.jinbu.jinbu.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {

    PostDTO getPost(Long id);
    void deletePost(Long id, String username);
    List<PostDTO> getPostsByUserId(Long userId);
    List<PostDTO> getPostsByUsername(String username);
    void createPost(Post post, Photo photo, MultipartFile multipartFile) throws IOException;
    List<PostDTO> retrievePosts(Pageable pageable);
    void likePost(Long id);
    void dislike(Long id);

    List<PostDTO> getPostsByUserIdWithPagination(Long userId, Pageable pageable);
}
