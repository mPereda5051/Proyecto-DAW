package com.jinbu.jinbu.web;

import com.jinbu.jinbu.DTOs.PostDTO;
import com.jinbu.jinbu.entities.Photo;
import com.jinbu.jinbu.service.ImageService.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/images")
@Tag(name = "Image Management"
        , description = "Operations related to images")
public class ImageController {

    ImageService imageService;

    @Operation(summary = "Upload image", description = "Saves photo metadata in localStorage and it sends the photo itself to an S3.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Image uploaded successfully", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error uploading image", content = @Content)
    })
    @PostMapping("/upload")
    public ResponseEntity<HttpStatus> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        imageService.store(file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Temporal/Testing, no podemos pedir all en la aplicacion final
    @Operation(summary = "Get all images", description = "Get all images from the database (Metadata and S3 link (Warning: Puede causar problemas de estabilidad))")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Images retrieved",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Photo.class))))
    })
    @GetMapping("/all")
    public ResponseEntity<List<Photo>> getAllImages() {
        return new ResponseEntity<>(imageService.retrieveAllImages(), HttpStatus.OK);
    }

    @Operation(summary = "Get image url by it's Id", description = "Fetch imageUrl by its ID (Long type).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "URL found", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Image not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<String> getUrlByImageId(@PathVariable Long id) {
        return new ResponseEntity<>(imageService.retrieveImageUrl(id), HttpStatus.OK);
    }

    @Operation(summary = "Delete photo by Id", description = "Deletes photo metadata and S3 file by Id (Long type).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Image deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Image not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteImage(@PathVariable Long id) {
        imageService.deleteImageById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/photos/search")
    public ResponseEntity<Page<Photo>> searchPhotos(
            @RequestParam(required = false) String iso,
            @RequestParam(required = false) String aperture,
            @RequestParam(required = false) String exposure,
            @RequestParam(required = false) String name,
            @PageableDefault(size = 10, sort = "date") Pageable pageable
    ) {
        Page<Photo> filteredPhotos = imageService.getFilteredPhotos(name, iso, aperture, exposure, pageable);
        return new ResponseEntity<>(filteredPhotos, HttpStatus.OK);
    }

    @Operation(summary = "Get Photos list by page number", description = "Get Photos list by page number in packs of 10.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Photos retrieved", content = @Content),
            @ApiResponse(responseCode = "404", description = "Photos not found", content = @Content)
    })
    @GetMapping("/retrieve/{pageNumber}")
    public ResponseEntity<Page<Photo>> retrievePostWithPagination(@PathVariable int pageNumber) {
        return new ResponseEntity<>(imageService.retrievePhotosPageable(pageNumber), HttpStatus.OK);
    }
}
