package com.jinbu.jinbu.web;

import com.jinbu.jinbu.DTOs.PostDTO;
import com.jinbu.jinbu.entities.Photo;
import com.jinbu.jinbu.entities.Post;
import com.jinbu.jinbu.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/posts")
@Tag(name = "Post management"
        , description = "Operations related to posts")
public class PostController {

    PostService postService;

    @Operation(summary = "Get Post by Id", description = "Fetch post information by its ID (Long type).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post found", content = @Content(schema = @Schema(implementation = PostDTO.class))),
            @ApiResponse(responseCode = "404", description = "Post not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
        return new ResponseEntity<>(postService.getPost(id), HttpStatus.OK);

    }

    // Temporal, solo para test
    @Operation(summary = "Get alls post", description = "Get all posts (WARNING: Puede causar problemas de optimizacion).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posts retrieved", content = @Content(schema = @Schema(implementation = PostDTO.class)))
    })
    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getPosts() {
        return new ResponseEntity<>(postService.getPosts(), HttpStatus.OK);
    }

    @Operation(summary = "Get all post from one user", description = "Fetch all post by a User ID (Long type).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posts retrieved", content = @Content(schema = @Schema(implementation = PostDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/all/{id}")
    public ResponseEntity<List<PostDTO>> getPostByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(postService.getPostsByUserId(userId), HttpStatus.OK);
    }

    @Operation(summary = "Save post by Id", description = "Saves post information (Tiene que contener la informacion del schema de post)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Post created", content = @Content(schema = @Schema(implementation = PostDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    public ResponseEntity<PostDTO> savePost(@RequestBody @Valid Post post) {
        return new ResponseEntity<>(postService.savePost(post), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete post by Id", description = "Delete posts by its ID (Long type).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Post deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Post not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/upload")
    public ResponseEntity<HttpStatus> createPostWithPhoto(@RequestBody Post post, @RequestBody Photo photo) {
        postService.createPostWithPhoto(post, photo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
