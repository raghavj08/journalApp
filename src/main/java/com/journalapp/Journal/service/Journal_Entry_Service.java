package com.journalapp.Journal.service;

import com.journalapp.Journal.entity.JournalEntry;
import com.journalapp.Journal.entity.User;
import com.journalapp.Journal.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class Journal_Entry_Service {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private User_Service user_Service;



    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username){
       try{
           User user = user_Service.findByUserName(username);
           journalEntry.setDate(LocalDateTime.now());
           JournalEntry saved = journalEntryRepository.save(journalEntry);
           user.getJournalEntries().add(saved);
           user_Service.saveUser(user);
       } catch (Exception e) {

           throw new RuntimeException("An error occurred while saving the entry",e);
       }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findByID(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String username){
        boolean removed = false;
        try {
            User user = user_Service.findByUserName(username);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                user_Service.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        }
        catch (Exception e){
            log.error("Error: ",e);
            throw new RuntimeException("An error occured while deleting the entry", e);
        }
        return removed;
    }

    public List<JournalEntry> findByUsername(String username){
        return null;
    }
}
