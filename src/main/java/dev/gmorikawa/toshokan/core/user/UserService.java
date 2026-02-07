package dev.gmorikawa.toshokan.core.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.core.auth.contract.Authorization;
import dev.gmorikawa.toshokan.core.auth.exception.UnauthorizedActionException;
import dev.gmorikawa.toshokan.core.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.core.user.enumerator.UserRole;
import dev.gmorikawa.toshokan.core.user.exception.AdminRemoveAttemptException;
import dev.gmorikawa.toshokan.core.user.exception.EmailNotAvailableException;
import dev.gmorikawa.toshokan.core.user.exception.UsernameNotAvailableException;
import dev.gmorikawa.toshokan.shared.query.Pagination;

@Service
public class UserService {

    private final Authorization authorization;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    // private final EmailClient emailClient;

    public UserService(
            Authorization authorization,
            UserRepository repository,
            PasswordEncoder passwordEncoder
            // EmailClient emailClient
    ) {
        this.authorization = authorization;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        // this.emailClient = emailClient;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public List<User> getAll(Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.page - 1, pagination.limit);
        Page<User> page = repository.findAll(pageable);

        return page.getContent();
    }

    public Integer countAll() {
        return (int) repository.count();
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }

    public User getById(LoggedUser loggedUser, UUID id) {
        if (loggedUser.getRole() == UserRole.READER && loggedUser.getId() != id) {
            throw new UnauthorizedActionException();
        }

        return repository.findById(id).orElse(null);
    }

    public User create(LoggedUser loggedUser, User entity) {
        authorization.checkUserRole(loggedUser, UserRole.ADMIN);

        if (!isEmailIsAvailable(entity.getEmail())) {
            throw new EmailNotAvailableException();
        }

        if (!isUsernameIsAvailable(entity.getUsername())) {
            throw new UsernameNotAvailableException();
        }

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        return repository.save(entity);
    }

    public User update(LoggedUser loggedUser, UUID id, User entity) {
        if (!loggedUser.hasRole(UserRole.ADMIN) && !loggedUser.isUserEqual(entity)) {
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

        user.setEmail(entity.getEmail());
        user.setUsername(entity.getUsername());

        return repository.save(user);
    }

    public boolean remove(LoggedUser loggedUser, UUID id) {
        authorization.checkUserRole(loggedUser, UserRole.ADMIN);

        Optional<User> user = repository.findById(id);

        if (user.isPresent()) {
            if (user.get().getRole() != UserRole.ADMIN) {
                repository.delete(user.get());
                return true;
            } else {
                throw new AdminRemoveAttemptException();
            }
        } else {
            return false;
        }
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
