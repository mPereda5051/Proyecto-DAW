package com.jinbu.backend_jinbu.service.metadata;

import org.springframework.stereotype.Service;

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
}
