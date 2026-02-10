package com.jinbu.backend_jinbu.service.storage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class StorageServiceImplementation implements StorageService {

    private final Path root;

    public StorageServiceImplementation(@Value("${app.storage.location}") String storageLocation) {
        root = Paths.get(storageLocation).toAbsolutePath().normalize();
        try { Files.createDirectories(root); } catch (IOException e) { throw new RuntimeException(e); }
    } 

    @Override
    public String store(MultipartFile file) throws IOException {
        validateFile(file);
        String filename = sanitizeFilename(file.getOriginalFilename());
        Path target = this.root.resolve(filename).normalize();
        // Prevenir path traversal
        if (!target.startsWith(this.root)) {
            throw new SecurityException("Invalid Path");
        }
        // Mediante el input stream del archivo subido lo copiamos a target path
        try (InputStream in = file.getInputStream()) {
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        }
        return filename;
    }

    @Override
    public Resource loadAsResource(String filename) throws IOException {
        Path file = root.resolve(filename).normalize();
        // Prevenir path traversal
        if (!file.startsWith(this.root)) throw new SecurityException("Invalid Path");
        // Comprobamos si existe
        if (!Files.exists(file)) throw new NoSuchFileException(filename);
        return new UrlResource(file.toUri());
    }

    @Override
    public boolean delete(String filename) throws IOException {
        Path file = root.resolve(filename).normalize();
        // Path traversal
        if (!file.startsWith(this.root)) throw new SecurityException("Invalid Path");
        return Files.deleteIfExists(file); // Usamos deleteIfExists en vez de delete para evitar problemas si el path es incorrecto
    }

    // helper
    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) throw new IllegalArgumentException("File empty.");
        // Añadir lógica validación archivos (Solo imágenes y tamaño máximo)
    }

    private String sanitizeFilename(String original) {
        String cleaned = Paths.get(original).getFileName().toString();

        // Se añade un UUID para evitar que dos archivos tengan el mismo nombre
        return UUID.randomUUID() + "-" + cleaned.replaceAll("[^A-Za-z0-9._-]", "_");
    }
}
