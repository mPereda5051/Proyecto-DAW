package com.jinbu.backend_jinbu.service;

import java.util.List;

import com.jinbu.backend_jinbu.pojo.Note;

public interface NoteManagementService {
    List<Note> getNotes();
    Note getNote(String id);
    void saveContact(Note note);
    void updateContact(String id, Note note);
    void removeContact(String id);
}
