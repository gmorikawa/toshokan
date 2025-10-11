package dev.gmorikawa.toshokan.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.shared.query.Pagination;
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

    public List<User> getAll() {
        return repository.findAll();
    }

    public List<User> getAll(Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.page - 1, pagination.size);
        Page<User> page = repository.findAll(pageable);
        
        return page.getContent();
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }

    public User getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public User create(User entity) {
        if(!isEmailIsAvailable(entity.getEmail())) {
            throw new EmailNotAvailableException();
        }

        if(!isUsernameIsAvailable(entity.getUsername())) {
            throw new UsernameNotAvailableException();
        }

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        return repository.save(entity);
    }

    public User update(UUID id, User entity) {
        if(!isEmailIsAvailable(entity.getEmail(), id)) {
            throw new EmailNotAvailableException();
        }

        if(!isUsernameIsAvailable(entity.getUsername(), id)) {
            throw new UsernameNotAvailableException();
        }

        Optional<User> result = repository.findById(id);

        if(result.isEmpty()) {
            return null;
        }

        User user = result.get();

        user.setFullname(entity.getFullname());
        user.setEmail(entity.getEmail());
        user.setUsername(entity.getUsername());

        return repository.save(user);
    }

    public User remove(UUID id) {
        Optional<User> user = repository.findById(id);

        if(!user.isEmpty()) {
            repository.delete(user.get());
        }

        return user.orElse(null);
    }

    private boolean isUsernameIsAvailable(String username) {
        return repository.findByUsername(username).isEmpty();
    }

    private boolean isUsernameIsAvailable(String username, UUID ignoreId) {
        return repository.findByUsername(username, ignoreId).isEmpty();
    }

    private boolean isEmailIsAvailable(String email) {
        return repository.findByEmail(email).isEmpty();
    }

    private boolean isEmailIsAvailable(String email, UUID ignoreId) {
        return repository.findByEmail(email, ignoreId).isEmpty();
    }
}
