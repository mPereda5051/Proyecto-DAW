package com.jinbu.backend_jinbu.pojo;

import java.util.UUID;

public class Note {
    private String id;
    private String content;
    private char category;

    public Note() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public char getCategory() {
        return this.category;
    }

    public void setCategory(char category) {
        this.category = category;
    }

}
