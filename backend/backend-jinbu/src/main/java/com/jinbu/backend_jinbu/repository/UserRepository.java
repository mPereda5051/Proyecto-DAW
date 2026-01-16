package com.jinbu.backend_jinbu.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jinbu.backend_jinbu.pojo.User;

@Repository
public class UserRepository implements RepoInterface<User, Integer> {
    private List<User> users = new ArrayList<>();

    @Override
    public List<User> getObjects() {
        return users;
    }

    @Override
    public User getObject(Integer index) {
        return users.get(index);
    }

    @Override
    public void saveObject(User generic) {
        users.add(generic);
    }

    @Override
    public void updateObject(Integer index, User generic) {
        users.set(index, generic);
    }

    @Override
    public void removeObject(Integer index) {
        users.remove(index.intValue());
    }
}
