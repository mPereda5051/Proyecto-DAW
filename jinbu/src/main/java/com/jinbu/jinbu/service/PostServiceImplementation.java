package com.jinbu.jinbu.service;

import com.jinbu.jinbu.DTOs.PostDTO;
import com.jinbu.jinbu.entities.Photo;
import com.jinbu.jinbu.entities.Post;
import com.jinbu.jinbu.entities.User;
import com.jinbu.jinbu.exceptions.EntityNotFoundException;
import com.jinbu.jinbu.mappers.PostMapper;
import com.jinbu.jinbu.repository.PhotoRepository;
import com.jinbu.jinbu.repository.PostRepository;
import com.jinbu.jinbu.repository.UserRepository;
import com.jinbu.jinbu.service.ImageService.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public PostDTO getPost(Long id) {
        return postMapper.toDTO(postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Post.class)));
    }

    @Override
    public void deletePost(Long id, String username) {
        Post post = postRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(id, Post.class));

        if (!post.getUser().getUsername().equals(username)) {
            return;
        }

        postRepository.delete(post);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDTO> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId)
                .stream()
                .map(postMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDTO> getPostsByUsername(String username) {
        return postRepository.findByUserUsername(username)
                .stream()
                .map(postMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public void createPost(Post post, Photo photo, MultipartFile multipartFile) throws IOException {

        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Photo photoSaved = imageService.store(photo, multipartFile)
                .orElseThrow(() -> new EntityNotFoundException(photo.getId(), Photo.class));

        post.setUser(user);
        post.setPhoto(photoSaved);
        photoSaved.setPost(post);

        postRepository.save(post);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDTO> retrievePosts(Pageable pageable) {
        return postRepository.findAll(pageable)
                .stream()
                .map(postMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public void likePost(Long id) {
        Post likedPost = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Post.class));

        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        likedPost.getLikedByUsers().add(user);
        postRepository.save(likedPost);
    }

    @Override
    @Transactional
    public void dislike(Long id) {
        Post unlikedPost = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Post.class));

        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        unlikedPost.getLikedByUsers().remove(user);
        postRepository.save(unlikedPost);
    }

    @Override
    public List<PostDTO> getPostsByUserIdWithPagination(Long userId, Pageable pageable) {
        List<Post> posts = postRepository.findPostsByUserId(userId, pageable);

        return posts.stream()
                .map(postMapper::toDTO)
                .toList();
    }


}
