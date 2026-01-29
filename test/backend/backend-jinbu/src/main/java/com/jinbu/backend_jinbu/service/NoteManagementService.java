package com.jinbu.backend_jinbu.service;

import java.util.List;

import com.jinbu.backend_jinbu.pojo.Note;

public interface NoteManagementService {
    List<Note> getNotes();
    Note getNote(String id);
    void saveNote(Note note);
    void updateNote(String id, Note note);
    void removeNote(String id);
}
