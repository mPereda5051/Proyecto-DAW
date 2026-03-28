package com.jinbu.jinbu.service.ImageService;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.jinbu.jinbu.entities.Photo;
import com.jinbu.jinbu.repository.PhotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Component
public class LocalImageStorage {

    @Autowired
    PhotoRepository photoRepository;

    public Long storeMetadata(MultipartFile file) throws IOException {
        try {
            String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());

            Metadata metadata = ImageMetadataReader.readMetadata(file.getInputStream());

            ExifSubIFDDirectory exifDirectory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

            Date date = exifDirectory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
            String iso = exifDirectory.getString(ExifSubIFDDirectory.TAG_ISO_EQUIVALENT);
            String aperture = exifDirectory.getString(ExifSubIFDDirectory.TAG_FNUMBER);
            String exposureTime = exifDirectory.getString(ExifSubIFDDirectory.TAG_EXPOSURE_TIME);
            String width = exifDirectory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH);
            String height = exifDirectory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT);

            Photo photo = new Photo(file.getOriginalFilename(), date, iso, aperture, exposureTime, width, height, extension);

            // Guardamos la photo en el repo
            photoRepository.save(photo);

            return photo.getId();


            // Add custom exceptions
        } catch (ImageProcessingException e) {
            System.err.println("Error de formato de imagen: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        // Create constant for object not found
        return -1L;
    }


}
