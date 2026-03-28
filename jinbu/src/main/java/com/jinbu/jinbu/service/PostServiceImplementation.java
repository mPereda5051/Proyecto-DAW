package com.jinbu.jinbu.service;

import com.jinbu.jinbu.DTOs.PostDTO;
import com.jinbu.jinbu.entities.Post;
import com.jinbu.jinbu.exceptions.EntityNotFoundException;
import com.jinbu.jinbu.mappers.PostMapper;
import com.jinbu.jinbu.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PostServiceImplementation implements PostService {

    PostRepository postRepository;
    PostMapper postMapper;

    @Override
    public PostDTO getPost(Long id) {
        return postMapper.toDTO(postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Post.class)));
    }

    @Override
    public PostDTO savePost(Post post) {
        return postMapper.toDTO(postRepository.save(post));
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<PostDTO> getPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::toDTO)
                .toList();
    }

    @Override
    public List<PostDTO> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId)
                .stream()
                .map(postMapper::toDTO)
                .toList();
    }
}
