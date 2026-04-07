package com.jinbu.jinbu.web;

import com.jinbu.jinbu.entities.Photo;
import com.jinbu.jinbu.service.ImageService.ImageServiceImplementation;
import com.jinbu.jinbu.service.ImageService.LocalImageStorage;
import com.jinbu.jinbu.service.ImageService.S3ImageStorage;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/images")
@Tag(name = "Image Management"
        , description = "Operations related to images")
public class ImageController {

    ImageServiceImplementation imageServiceImplementation;

    @Operation(summary = "Upload image", description = "Saves photo metadata in localStorage and it sends the photo itself to an S3.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Image uploaded successfully", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error uploading image", content = @Content)
    })
    @PostMapping("/upload")
    public ResponseEntity<HttpStatus> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        imageServiceImplementation.store(file);
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
        return new ResponseEntity<>(imageServiceImplementation.retrieveAllImages(), HttpStatus.OK);
    }

    @Operation(summary = "Get image url by it's Id", description = "Fetch imageUrl by its ID (Long type).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "URL found", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Image not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<String> getUrlByImageId(@PathVariable Long id) {
        return new ResponseEntity<>(imageServiceImplementation.retrieveImageUrl(id), HttpStatus.OK);
    }

    @Operation(summary = "Delete photo by Id", description = "Deletes photo metadata and S3 file by Id (Long type).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Image deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Image not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteImage(@PathVariable Long id) {
        imageServiceImplementation.deleteImageById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
