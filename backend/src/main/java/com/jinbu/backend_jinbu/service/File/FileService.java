package com.jinbu.backend_jinbu.service.File;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.jinbu.backend_jinbu.entities.FileEntity;
import com.jinbu.backend_jinbu.response.ResponseFile;

public interface FileService {
    //Permite almacenar o cargar archivos a la BD
    FileEntity store(MultipartFile file) throws IOException;

    //Permite descargar archivos de la BD
    Optional<FileEntity> getFile(UUID id);

    //Permite consultar la lista de archivos cargados a la BD
    List<ResponseFile> getAllFiles();
}
