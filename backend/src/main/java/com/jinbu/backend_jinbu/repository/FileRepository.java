package com.jinbu.backend_jinbu.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jinbu.backend_jinbu.entities.FileEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, UUID>{

    

}