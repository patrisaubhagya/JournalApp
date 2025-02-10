package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class JournalEntryService implements JournalService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    @Override
    public List<JournalEntry> search() {
        return journalEntryRepository.findAll();
    }

    @Override
    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Override
    public boolean create(JournalEntry journalEntry) {
        if (journalEntryRepository.findById(journalEntry.getId()) != null) {
            journalEntryRepository.save(journalEntry);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void createWithUsername(JournalEntry journalEntry, String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User  not found");
        }

        journalEntry.setDate(LocalDateTime.now());

        try {
            JournalEntry savedEntry = journalEntryRepository.save(journalEntry);
            if (user.getJournalEntries() == null) {
                user.setJournalEntries(new ArrayList<>()); // Initialize if null
            }
            user.getJournalEntries().add(savedEntry);
            System.out.println(userService.save(user)); // Ensure the user is saved with the updated journal entries
        } catch (Exception e) {
            logger.info(e.getMessage());
//            System.out.println("Error saving journal entry: " + e.getMessage());
            throw new RuntimeException("Failed to save journal entry", e);
        }
    }

    @Override
    @Transactional
    public boolean delete(ObjectId id, String username) {
        boolean result = false;

        try {
            User user = userService.findByUsername(username);
            result = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (result) {
                userService.save(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete journal entry: " + e.getMessage());
        }
        return result;
    }

}
