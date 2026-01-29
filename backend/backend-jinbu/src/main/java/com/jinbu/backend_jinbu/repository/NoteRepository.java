package com.jinbu.backend_jinbu.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jinbu.backend_jinbu.pojo.Note;

@Repository
public class NoteRepository implements RepoInterface<Note, Integer> {
    private List<Note> notes = new ArrayList<>();

    @Override
    public List<Note> getObjects() {
        return notes;
    }

    @Override
    public Note getObject(Integer index) {
        return notes.get(index);
    }

    @Override
    public void saveObject(Note generic) {
        notes.add(generic);
    }

    @Override
    public void updateObject(Integer index, Note generic) {
        notes.set(index, generic);
    }

    @Override
    public void removeObject(Integer index) {
        notes.remove(index.intValue());
    }

}
