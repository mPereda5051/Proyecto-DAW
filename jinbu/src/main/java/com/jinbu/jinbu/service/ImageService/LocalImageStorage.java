package com.jinbu.jinbu.service.ImageService;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.jinbu.jinbu.entities.Photo;
import com.jinbu.jinbu.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Component
public class LocalImageStorage {

    @Autowired
    PhotoRepository photoRepository;

    public Photo retrieveMetadata(MultipartFile file) throws IOException {
        try {
            String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());

            Metadata metadata = ImageMetadataReader.readMetadata(file.getInputStream());

            ExifSubIFDDirectory exifDirectory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

            // Si la photo no tiene metadata devolvemos una foto vacia (evitamos el nullpointer)
            if (exifDirectory == null) {
                return new Photo();
            }

            Date date = exifDirectory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
            Integer iso = exifDirectory.getInt(ExifSubIFDDirectory.TAG_ISO_EQUIVALENT);
            Double aperture = exifDirectory.getDouble(ExifSubIFDDirectory.TAG_FNUMBER);
            Double exposureTime = exifDirectory.getDouble(ExifSubIFDDirectory.TAG_EXPOSURE_TIME);
            String width = exifDirectory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH);
            String height = exifDirectory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT);

            return new Photo(file.getOriginalFilename(), date, iso, aperture, exposureTime, width, height, extension);

            // Add custom exceptions
        } catch (ImageProcessingException e) {
            System.err.println("Error de formato de imagen: " + e.getMessage());
            System.err.println("Error null pointer Image");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            System.err.println("Error null pointer IOException");
        } catch (MetadataException e) {
            System.err.println("Error null pointer MetadataException");
            throw new RuntimeException(e);
        }

        // Cambiar por excepcion personalizada al no recibir una foto correcta
        return new Photo();
    }

    public Long storeMetadata(Photo photo) throws IOException {
        // Guardamos la photo en el repo
        photoRepository.save(photo);
        return photo.getId();
    }

    public void deleteImageMetadata(Long id) {
        photoRepository.deleteById(id);
    }

}
