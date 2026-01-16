package com.jinbu.backend_jinbu.service;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinbu.backend_jinbu.pojo.Note;
import com.jinbu.backend_jinbu.repository.NoteRepository;

@Service
public class NoteManagementImpl implements NoteManagementService {
    @Autowired
    private NoteRepository noteRepository;

    @Override
    public List<Note> getNotes() {
        return noteRepository.getObjects();
    }

    @Override
    public Note getNote(String id) {
        return noteRepository.getObject(getIndexById(id));
    }

    @Override
    public void saveContact(Note note) {
        noteRepository.saveObject(note);
    }

    @Override
    public void updateContact(String id, Note note) {
        noteRepository.updateObject(getIndexById(id), note);
    }

    @Override
    public void removeContact(String id) {
        noteRepository.removeObject(getIndexById(id));
    }
    
    public int getIndexById(String id) {
        return IntStream.range(0, noteRepository.getObjects().size())
        .filter(index -> noteRepository.getObjects().get(index).getId().equals(id))
        .findFirst()
        .orElseThrow();
    }
}
