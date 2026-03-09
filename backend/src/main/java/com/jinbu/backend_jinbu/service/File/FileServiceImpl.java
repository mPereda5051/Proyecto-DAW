package com.jinbu.backend_jinbu.service.File;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jinbu.backend_jinbu.entities.FileEntity;
import com.jinbu.backend_jinbu.repository.FileRepository;
import com.jinbu.backend_jinbu.response.ResponseFile;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public FileEntity store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileEntity fileEntity = FileEntity.builder()
            .name(fileName)
            .type(file.getContentType())
            .data(file.getBytes())
            .build();
        return fileRepository.save(fileEntity);
    }

    @Override
    public Optional<FileEntity> getFile(UUID id) {
        return fileRepository.findById(id);
    }

    @Override
    public List<ResponseFile> getAllFiles() {
        return fileRepository.findAll().stream().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/")
                .path(dbFile.getId().toString())
                .toUriString();

            return ResponseFile.builder()
                .name(dbFile.getName())
                .url(fileDownloadUri)
                .type(dbFile.getType())
                .size(dbFile.getData().length)
                .build();
        }).collect(Collectors.toList());
    }
}
