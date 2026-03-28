package com.jinbu.jinbu.repository;

import com.jinbu.jinbu.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
