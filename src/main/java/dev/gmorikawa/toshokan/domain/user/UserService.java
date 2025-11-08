package dev.gmorikawa.toshokan.domain.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.auth.Authorization;
import dev.gmorikawa.toshokan.auth.exception.UnauthorizedActionException;
import dev.gmorikawa.toshokan.domain.user.enumerator.UserRole;
import dev.gmorikawa.toshokan.domain.user.exception.EmailNotAvailableException;
import dev.gmorikawa.toshokan.domain.user.exception.UsernameNotAvailableException;
import dev.gmorikawa.toshokan.shared.query.Pagination;

@Service
public class UserService {

    private final Authorization authorization;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            Authorization authorization,
            UserRepository repository,
            PasswordEncoder passwordEncoder
    ) {
        this.authorization = authorization;
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

    public User create(User client, User entity) {
        authorization.checkUserRole(client, UserRole.ADMIN);

        if (!isEmailIsAvailable(entity.getEmail())) {
            throw new EmailNotAvailableException();
        }

        if (!isUsernameIsAvailable(entity.getUsername())) {
            throw new UsernameNotAvailableException();
        }

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        return repository.save(entity);
    }

    public User update(User client, UUID id, User entity) {
        if (!client.hasRole(UserRole.ADMIN) && !client.isIdEqual(entity)) {
            throw new UnauthorizedActionException();
        }

        if (!isEmailIsAvailable(entity.getEmail(), id)) {
            throw new EmailNotAvailableException();
        }

        if (!isUsernameIsAvailable(entity.getUsername(), id)) {
            throw new UsernameNotAvailableException();
        }

        Optional<User> result = repository.findById(id);

        if (result.isEmpty()) {
            return null;
        }

        User user = result.get();

        user.setFullname(entity.getFullname());
        user.setEmail(entity.getEmail());
        user.setUsername(entity.getUsername());

        return repository.save(user);
    }

    public User remove(User client, UUID id) {
        authorization.checkUserRole(client, UserRole.ADMIN);

        Optional<User> user = repository.findById(id);

        if (!user.isEmpty()) {
            repository.delete(user.get());
        }

        return user.orElse(null);
    }

    public User activate(UUID id) {
        Optional<User> user = repository.findById(id);

        if (user.isPresent()) {
            User userToActivate = user.get();
            userToActivate.activate();
            return repository.save(userToActivate);
        } else {
            return null;
        }
    }

    public User block(UUID id) {
        Optional<User> user = repository.findById(id);

        if (user.isPresent()) {
            User userToBlock = user.get();
            userToBlock.block();
            return repository.save(userToBlock);
        } else {
            return null;
        }
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
