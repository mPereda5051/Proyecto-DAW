package com.jinbu.jinbu.web;

import com.jinbu.jinbu.DTOs.PostDTO;
import com.jinbu.jinbu.entities.Post;
import com.jinbu.jinbu.service.PostService;
import com.jinbu.jinbu.service.PostServiceImplementation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

    PostServiceImplementation postServiceImplementation;

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
        return new ResponseEntity<>(postServiceImplementation.getPost(id), HttpStatus.OK);

    }

    // Temporal, solo para test
    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getPosts() {
        return new ResponseEntity<>(postServiceImplementation.getPosts(), HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<PostDTO>> getPostByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(postServiceImplementation.getPostsByUserId(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostDTO> savePost(@RequestBody @Valid Post post) {
        return new ResponseEntity<>(postServiceImplementation.savePost(post), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable Long id) {
        postServiceImplementation.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
