package com.rishav.journalApp.controller;

import com.rishav.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {


//    private final Map<Long, JournalEntry> journalEntries = new HashMap<>();
//
//    //endpoints
//    @GetMapping("/get-journals")
//    public List<JournalEntry> getAll() {
//        return new ArrayList<>(journalEntries.values());
//    }
//
//    @PostMapping("/create-journals")
//    public boolean createEntry(@RequestBody JournalEntry myEntry){
//        journalEntries.put(myEntry.getId(), myEntry);
//        return true;
//    }
//
//    @GetMapping("/get-journal/id/{myId}")
//    public JournalEntry getEntryById(@PathVariable Long myId){
//        return journalEntries.get(myId);
//    }
//
//    @DeleteMapping("/delete-journal/id/{journalId}")
//    public JournalEntry deleteEntryById(@PathVariable Long journalId){
//        return journalEntries.remove(journalId);
//    }
//
//    @PutMapping("/update-journal/id/{journalId}")
//    public JournalEntry updateEntryById(@PathVariable Long journalId, @RequestBody JournalEntry myUpdatedEntry){
//        return journalEntries.put(journalId, myUpdatedEntry);
//    }
}
