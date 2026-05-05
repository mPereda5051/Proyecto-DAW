package com.jinbu.jinbu.service;

import com.jinbu.jinbu.DTOs.PostDTO;
import com.jinbu.jinbu.entities.Photo;
import com.jinbu.jinbu.entities.Post;
import com.jinbu.jinbu.exceptions.EntityNotFoundException;
import com.jinbu.jinbu.mappers.PostMapper;
import com.jinbu.jinbu.repository.PhotoRepository;
import com.jinbu.jinbu.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PostServiceImplementation implements PostService {

    PhotoRepository photoRepository;
    PostRepository postRepository;
    PostMapper postMapper;

    @Override
    public PostDTO getPost(Long id) {
        return postMapper.toDTO(postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Post.class)));
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<PostDTO> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId)
                .stream()
                .map(postMapper::toDTO)
                .toList();
    }

    @Override
    public void createPost(Post post, Photo photo) {
        post.setPhoto(photo);
        photo.setPost(post);
    }

    @Override
    public List<PostDTO> retrievePosts(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        return postRepository.findAll(pageable)
                .stream()
                .map(postMapper::toDTO)
                .toList();
    }
}
