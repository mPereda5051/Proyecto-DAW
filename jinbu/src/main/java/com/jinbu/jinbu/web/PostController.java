package com.jinbu.jinbu.web;

import com.jinbu.jinbu.DTOs.PostDTO;
import com.jinbu.jinbu.DTOs.PostWithPhotoDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @GetMapping("/all/{id}")
    public ResponseEntity<List<PostDTO>> getPostByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(postService.getPostsByUserId(userId), HttpStatus.OK);
    }

    @Operation(summary = "Get all post from one user by username", description = "Fetch all post by a username.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posts retrieved", content = @Content(schema = @Schema(implementation = PostDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/user/{username}")
    public ResponseEntity<List<PostDTO>> getPostsByUsername(@PathVariable String username) {
        return new ResponseEntity<>(postService.getPostsByUsername(username), HttpStatus.OK);
    }

    @Operation(summary = "Save post with text and image", description = "Saves post information (Tiene que contener la informacion del schema de post)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Post created", content = @Content(schema = @Schema(implementation = PostDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping("/upload")
    public ResponseEntity<HttpStatus> savePost(
            @RequestPart("post") @Valid Post post,
            @RequestPart("photo") Photo photo,
            @RequestPart("file") MultipartFile file
    ) throws IOException {
        System.out.println(photo.getExtension());
        postService.createPost(post, photo, file);

        return new ResponseEntity<>(HttpStatus.CREATED);
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

    @Operation(summary = "Get Posts list by page number", description = "Get Post list by page number in packs of 10.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Posts retrieved", content = @Content),
            @ApiResponse(responseCode = "404", description = "Post not found", content = @Content)
    })
    @GetMapping("/retrieve/{pageNumber}")
    public ResponseEntity<List<PostDTO>> retrievePostWithPagination(@PageableDefault(size = 30, sort = "createdOn")Pageable pageable) {
        System.out.println(postService.retrievePosts(pageable));
        return new ResponseEntity<>(postService.retrievePosts(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Like post by Id", description = "Like a post by its id (Long type).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Posts liked", content = @Content),
            @ApiResponse(responseCode = "404", description = "Post not found", content = @Content)
    })
    @PutMapping("/{id}/like")
    public ResponseEntity<HttpStatus> likePost(@PathVariable Long id) {
        postService.likePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Unlike post by Id", description = "Unlike a post by its id (Long type).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Posts unliked", content = @Content),
            @ApiResponse(responseCode = "404", description = "Post not unliked", content = @Content)
    })
    @PutMapping("/{id}/dislike")
    public ResponseEntity<HttpStatus> dislikePost(@PathVariable Long id) {
        postService.dislike(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
