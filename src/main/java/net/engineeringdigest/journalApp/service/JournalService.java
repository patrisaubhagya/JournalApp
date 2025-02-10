package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface JournalService {
    List<JournalEntry> search();
    Optional<JournalEntry> findById(ObjectId id);
    void createWithUsername(JournalEntry journalEntry, String username);
    boolean create(JournalEntry journalEntry);
    boolean delete(ObjectId id, String username);
}
