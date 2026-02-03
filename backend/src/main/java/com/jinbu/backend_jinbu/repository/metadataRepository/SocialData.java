package com.jinbu.backend_jinbu.repository.metadataRepository;

import org.springframework.data.repository.CrudRepository;

import com.jinbu.backend_jinbu.entities.Metadata.ModerationData;

public interface SocialData extends CrudRepository<ModerationData, Long>{

}
