package com.jinbu.backend_jinbu.pojo;

import java.util.UUID;

public class User {
    private String id;
    private String username;
    private String group;

    public User() {
        this.id = UUID.randomUUID().toString();
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
