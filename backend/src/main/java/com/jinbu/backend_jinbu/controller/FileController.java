package com.jinbu.backend_jinbu.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jinbu.backend_jinbu.entities.FileEntity;
import com.jinbu.backend_jinbu.response.ResponseFile;
import com.jinbu.backend_jinbu.service.File.FileService;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            FileEntity fileEntity = fileService.store(file);
            return ResponseEntity.ok("File uploaded successfully with ID: " + fileEntity.getId());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Could not upload file: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ResponseFile>> getAllFiles() {
        return ResponseEntity.ok(fileService.getAllFiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable UUID id) {
        return fileService.getFile(id)
                .map(file -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                        .header(HttpHeaders.CONTENT_TYPE, file.getType())
                        .body(file.getData()))
                .orElse(ResponseEntity.notFound().build());
    }
}
