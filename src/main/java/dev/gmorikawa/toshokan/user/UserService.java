package dev.gmorikawa.toshokan.user;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.user.exception.EmailNotAvailableException;
import dev.gmorikawa.toshokan.user.exception.UsernameNotAvailableException;

@Service
public class UserService {
    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }

    public User getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public User insert(User entity) {
        if(!checkEmailIsAvailable(entity.getEmail())) {
            throw new EmailNotAvailableException();
        }

        if(!checkUsernameIsAvailable(entity.getUsername())) {
            throw new UsernameNotAvailableException();
        }

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        return repository.save(entity);
    }

    public User update(String id, User entity) {
        if(!checkEmailIsAvailable(entity.getEmail(), id)) {
            throw new EmailNotAvailableException();
        }

        if(!checkUsernameIsAvailable(entity.getUsername(), id)) {
            throw new UsernameNotAvailableException();
        }

        Optional<User> result = repository.findById(id);

        if(result.isEmpty()) {
            return null;
        }

        User user = result.get();

        user.setEmail(entity.getEmail());
        user.setUsername(entity.getUsername());

        return repository.save(user);
    }

    public User remove(String id) {
        Optional<User> user = repository.findById(id);

        if(!user.isEmpty()) {
            repository.delete(user.get());
        }

        return user.orElse(null);
    }

    private boolean checkUsernameIsAvailable(String username) {
        return repository.findByUsername(username).isEmpty();
    }

    private boolean checkUsernameIsAvailable(String username, String ignoreId) {
        return repository.findByUsername(username, ignoreId).isEmpty();
    }

    private boolean checkEmailIsAvailable(String email) {
        return repository.findByEmail(email).isEmpty();
    }

    private boolean checkEmailIsAvailable(String email, String ignoreId) {
        return repository.findByEmail(email, ignoreId).isEmpty();
    }
}
