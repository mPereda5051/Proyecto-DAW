package com.jinbu.backend_jinbu.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jinbu.backend_jinbu.service.storage.StorageService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@AllArgsConstructor
@RestController
@RequestMapping("/files")
public class StorageController {

    StorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> upload(@RequestParam MultipartFile file) throws IOException {
        String filename = storageService.store(file);
        // Respuesta del servidor. Devuelve nombre del archivo y url de descarga
        Map<String, String> responseBody = Map.of(
            "filename", filename,
            "downloadUrl", "/files/download/" + URLEncoder.encode(filename, StandardCharsets.UTF_8)
        );

        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }
    
    @GetMapping("/download({filename:.+}")
    public ResponseEntity<Resource> download(@PathVariable String fileName) throws IOException {
        Resource resource = storageService.loadAsResource(fileName);
        // Implementar lógica con servlets para sacar más información
    }
    
}
