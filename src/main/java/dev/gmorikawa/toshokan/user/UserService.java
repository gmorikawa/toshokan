package dev.gmorikawa.toshokan.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.user.exception.EmailNotAvailableException;
import dev.gmorikawa.toshokan.user.exception.UsernameNotAvailableException;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User insert(User user) {
        if(!checkEmailIsAvailable(user.getEmail())) {
            throw new EmailNotAvailableException();
        }

        if(!checkUsernameIsAvailable(user.getUsername())) {
            throw new UsernameNotAvailableException();
        }

        return repository.save(user);
    }

    public User update(String id, User user) {
        User current = repository.getReferenceById(id);

        if(current != null) {
            if(!checkEmailIsAvailable(user.getEmail(), id)) {
                throw new EmailNotAvailableException();
            }
    
            if(!checkUsernameIsAvailable(user.getUsername(), id)) {
                throw new UsernameNotAvailableException();
            }

            current.setEmail(user.getEmail());
            current.setUsername(user.getUsername());
        }

        return repository.save(current);
    }

    public boolean remove(String id) {
        User user = repository.getReferenceById(id);
        repository.delete(user);

        return true;
    }

    private boolean checkUsernameIsAvailable(String username) {
        return repository.findUserByUsername(username).isEmpty();
    }

    private boolean checkUsernameIsAvailable(String username, String ignoreId) {
        return repository.findUserByUsername(username, ignoreId).isEmpty();
    }

    private boolean checkEmailIsAvailable(String email) {
        return repository.findUserByEmail(email).isEmpty();
    }

    private boolean checkEmailIsAvailable(String email, String ignoreId) {
        return repository.findUserByEmail(email, ignoreId).isEmpty();
    }
}
