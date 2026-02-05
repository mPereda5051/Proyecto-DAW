package com.jinbu.backend_jinbu.service.metadata;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;
import com.jinbu.backend_jinbu.entities.metadata.ContentData;
import com.jinbu.backend_jinbu.repository.metadataRepository.ContentDataRepository;
import com.jinbu.backend_jinbu.repository.metadataRepository.ExifDataRepository;
import com.jinbu.backend_jinbu.repository.metadataRepository.ModerationDataRepository;
import com.jinbu.backend_jinbu.repository.metadataRepository.SocialDataRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MetadataServiceImplementation implements MetadataService {

    ContentDataRepository contentDataRepository;
    ExifDataRepository exifDataRepository;
    ModerationDataRepository moderationDataRepository;
    SocialDataRepository socialDataRepository;

    public Metadata loadMetadata(File file) throws IOException, ImageProcessingException {
    return ImageMetadataReader.readMetadata(file);
    }

    // Metadata-Reader
    public Optional<Date> getCreationDate(Metadata metadata) {
        return Optional.ofNullable(metadata)
            .map(m -> m.getFirstDirectoryOfType(ExifIFD0Directory.class))
            .map(d -> d.getDate(ExifIFD0Directory.TAG_DATETIME_ORIGINAL));
    }

    public Optional<String> getMaker(Metadata metadata) {
        return Optional.ofNullable(metadata)
            .map(m -> m.getFirstDirectoryOfType(ExifIFD0Directory.class))
            .map(d -> d.getDescription(ExifIFD0Directory.TAG_MAKE));
    }

    public Optional<String> getBodyModel(Metadata metadata) {
        return Optional.ofNullable(metadata)
            .map(m -> m.getFirstDirectoryOfType(ExifIFD0Directory.class))
            .map(d -> d.getDescription(ExifIFD0Directory.TAG_MODEL));
    }

    public Optional<Integer> getISO(Metadata metadata) {
        return Optional.ofNullable(metadata)
            .map(m -> m.getFirstDirectoryOfType(ExifSubIFDDirectory.class))
            .map(d -> {
                try {
                    return d.getInt(ExifSubIFDDirectory.TAG_ISO_EQUIVALENT);
                } catch (MetadataException e) {
                    return null;
                }
            });
    }

    public Optional<String> getAperture(Metadata metadata) {
        return Optional.ofNullable(metadata)
            .map(m -> m.getFirstDirectoryOfType(ExifIFD0Directory.class))
            .map(d -> d.getDescription(ExifIFD0Directory.TAG_FNUMBER));
    }

    public Optional<String> getGpsLocation(Metadata metadata) {
        return Optional.ofNullable(metadata)
            .map(m -> m.getFirstDirectoryOfType(GpsDirectory.class))
            .map(d -> d.getGeoLocation().toString());
    }
}
