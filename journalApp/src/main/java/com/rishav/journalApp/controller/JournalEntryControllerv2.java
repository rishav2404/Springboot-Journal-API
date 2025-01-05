package com.rishav.journalApp.controller;

import com.rishav.journalApp.entity.JournalEntry;
import com.rishav.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")  //get added to all endpoint below
public class JournalEntryControllerv2 {

    @Autowired
    private JournalEntryService journalEntryService;

    //endpoints
    @GetMapping("/get-journals")
    public ResponseEntity<?> getAll() {
        try {
            List<JournalEntry> all = journalEntryService.getAll();
            return new ResponseEntity<>(all,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create-journal")
    public ResponseEntity<Object> createEntry(@RequestBody JournalEntry myEntry){
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveJournalEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/get-journal/id/{myId}")
    public ResponseEntity<?> getEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntry> jouEntry = journalEntryService.getJournalEntryById(myId);
        try {
            if(jouEntry.isPresent()) {
                return new ResponseEntity<>(jouEntry.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-journal/id/{journalId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId journalId){
        try {
            journalEntryService.deleteJournalEntryById(journalId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-journal/id/{journalId}")
    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId journalId, @RequestBody JournalEntry newEntry){
        JournalEntry oldEntry = journalEntryService.getJournalEntryById(journalId).orElse(null);
        if(oldEntry != null) {
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()
                    ? newEntry.getTitle()
                    : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty()
                    ? newEntry.getContent()
                    : oldEntry.getContent());
            journalEntryService.saveJournalEntry(oldEntry);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>("Journal with provided id not found!",HttpStatus.NOT_FOUND);
    }
}
