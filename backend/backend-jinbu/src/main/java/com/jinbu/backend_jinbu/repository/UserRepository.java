package com.jinbu.backend_jinbu.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jinbu.backend_jinbu.Entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
}
