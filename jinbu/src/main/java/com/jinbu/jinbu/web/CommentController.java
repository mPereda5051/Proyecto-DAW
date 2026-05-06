package com.jinbu.jinbu.web;

import com.jinbu.jinbu.entities.Comment;
import com.jinbu.jinbu.entities.Photo;
import com.jinbu.jinbu.service.CommentService;
import com.jinbu.jinbu.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/comment")
@Tag(name = "Comment Management"
        , description = "Operations related to post comments")
public class CommentController {

    CommentService commentService;

    @Operation(summary = "Upload comment", description = "Saves comment.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment uploaded successfully", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error uploading comment", content = @Content)
    })
    @PostMapping("/upload")
    public ResponseEntity<HttpStatus> uploadComment(@RequestParam Comment comment) {
        commentService.createComment(comment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(summary = "Get comment by it's Id", description = "Fetch comment by its ID (Long type).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment found", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Comment not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.getComment(id), HttpStatus.OK);
    }

    @Operation(summary = "Retrieve comments by PageNumber", description = "Retrieve 15 comments by pageNumber.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments found", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Comments not found", content = @Content)
    })
    @GetMapping("/{pageNumber}")
    public ResponseEntity<Page<Comment>> getCommentById(@PathVariable int pageNumber) {
        return new ResponseEntity<>(commentService.retrieveComments(pageNumber), HttpStatus.OK);
    }

    @Operation(summary = "Delete comment by Id", description = "Deletes comment by Id (Long type).")
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
