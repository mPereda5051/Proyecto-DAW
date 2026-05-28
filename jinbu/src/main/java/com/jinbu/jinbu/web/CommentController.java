package com.jinbu.jinbu.web;

import com.jinbu.jinbu.entities.Comment;
import com.jinbu.jinbu.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/comment")
@Tag(name = "Comment Management"
        , description = "Operations related to post comments")
public class CommentController {

    CommentService commentService;

    @Operation(summary = "Create comment", description = "Saves comment for a specific post.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment created successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid comment data", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error creating comment", content = @Content)
    })
    @PostMapping("/post/{postId}")
    public ResponseEntity<Comment> createComment(@PathVariable Long postId, @RequestBody String content) {
        return new ResponseEntity<>(commentService.createComment(postId, content), HttpStatus.CREATED);
    }

    @Operation(summary = "Get comments by post Id", description = "Fetch all comments for a post.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments found", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Comment.class)))),
            @ApiResponse(responseCode = "404", description = "Post not found", content = @Content)
    })
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable Long postId) {
        return new ResponseEntity<>(commentService.getCommentsByPostId(postId), HttpStatus.OK);
    }

    @Operation(summary = "Get comment by Id", description = "Fetch comment by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment found", content = @Content(schema = @Schema(implementation = Comment.class))),
            @ApiResponse(responseCode = "404", description = "Comment not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.getComment(id), HttpStatus.OK);
    }

    @Operation(summary = "Delete comment by Id", description = "Deletes comment by Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Comment deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Comment not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
