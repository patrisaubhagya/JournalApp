package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> search();
    Optional<User> findById(ObjectId id);
    List<User> getAllUsers();
    boolean save(User user);
    boolean delete(ObjectId id);
    User findByUsername(String username);
    boolean saveNewUser(User user);
    User saveAdmin(User user);
}
