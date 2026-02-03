package com.jinbu.backend_jinbu.repository.metadataRepository;

import org.springframework.data.repository.CrudRepository;

import com.jinbu.backend_jinbu.entities.Metadata.ExifData;

public interface ExifDataRepository extends CrudRepository<ExifData, Long> {

}
