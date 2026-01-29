package com.jinbu.backend_jinbu.service;

import org.springframework.stereotype.Service;

import com.jinbu.backend_jinbu.Entities.Group;
import com.jinbu.backend_jinbu.repository.GroupRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GroupServiceImplementation implements GroupService {

    GroupRepository groupRepository;

    public Group getGroup(Long id) {
        return groupRepository.findById(id).get();
    }
}
