package com.jinbu.backend_jinbu.service.metadata;

import org.springframework.stereotype.Service;

import com.jinbu.backend_jinbu.entities.metadata.ContentData;
import com.jinbu.backend_jinbu.repository.metadataRepository.ContentDataRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ContentDataServiceImplementation implements ContentDataService{

    ContentDataRepository contentDataRepository;

    @Override
    public ContentData saveContentData(ContentData contentData) {
        return contentDataRepository.save(contentData);
    }
}   
