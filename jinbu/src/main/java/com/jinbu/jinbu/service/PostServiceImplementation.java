package com.jinbu.jinbu.service;

import com.jinbu.jinbu.DTOs.PostDTO;
import com.jinbu.jinbu.entities.Photo;
import com.jinbu.jinbu.entities.Post;
import com.jinbu.jinbu.exceptions.EntityNotFoundException;
import com.jinbu.jinbu.mappers.PostMapper;
import com.jinbu.jinbu.repository.PhotoRepository;
import com.jinbu.jinbu.repository.PostRepository;
import com.jinbu.jinbu.service.ImageService.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Service
public class PostServiceImplementation implements PostService {

    PhotoRepository photoRepository;
    PostRepository postRepository;
    PostMapper postMapper;
    ImageService imageService;

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
    public List<PostDTO> getPostsByUsername(String username) {
        return postRepository.findByUserUsername(username)
                .stream()
                .map(postMapper::toDTO)
                .toList();
    }

    @Override
    public void createPost(Post post, Photo photo, MultipartFile multipartFile) throws IOException {
        Photo photoSaved = imageService.store(photo, multipartFile)
                .orElseThrow(() -> new EntityNotFoundException(photo.getId(), Photo.class));

        post.setPhoto(photoSaved);
        postRepository.save(post);
    }

    @Override
    public List<PostDTO> retrievePosts(Pageable pageable) {
        return postRepository.findAll(pageable)
                .stream()
                .map(postMapper::toDTO)
                .toList();
    }

    @Override
    public void likePost(Long id) {
        Post likedPost = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Post.class));

        likedPost.setLikes(likedPost.getLikes() + 1L);
    }

    @Override
    public void dislike(Long id) {
        Post unlikedPost = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Post.class));

        unlikedPost.setLikes(unlikedPost.getLikes() - 1L);
    }


}
