package com.jinbu.backend_jinbu.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jinbu.backend_jinbu.pojo.Note;
import com.jinbu.backend_jinbu.service.NoteManagementService;


@RestController
public class NotesController {

    @Autowired
    private NoteManagementService noteManagementService;

    @GetMapping("/note/all")
    public ResponseEntity<List<Note>> getNotes() {
        List<Note> notes = noteManagementService.getNotes();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }
    
    @GetMapping("/note/{id}")
    public ResponseEntity<Note> getNote(@PathVariable String id) {
        Note note = noteManagementService.getNote(id);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }
    
    @PostMapping("/note")
    public ResponseEntity<HttpStatus> createNote(@RequestBody Note note) {
        noteManagementService.saveNote(note);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @PutMapping("/note/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable String id, @RequestBody Note note) {
        noteManagementService.updateNote(id, note);
        return new ResponseEntity<>(noteManagementService.getNote(id), HttpStatus.OK);
    }

    @DeleteMapping("/note/{id}")
    public ResponseEntity<HttpStatus> removeContact(@PathVariable String id) {
        noteManagementService.removeNote(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
