package dev.gmorikawa.toshokan.author;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.auth.exception.UnauthorizedActionException;
import dev.gmorikawa.toshokan.user.User;
import dev.gmorikawa.toshokan.user.enumerator.UserRole;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public List<Author> getAll() {
        return repository.findAll();
    }

    public Author getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Author getByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    public Author create(User requestor, Author entity) {
        if (!requestor.hasRole(Set.of(UserRole.ADMIN, UserRole.LIBRARIAN))) {
            throw new UnauthorizedActionException();
        }

        return repository.save(entity);
    }

    public Author update(User requestor, String id, Author entity) {
        if (!requestor.hasRole(Set.of(UserRole.ADMIN, UserRole.LIBRARIAN))) {
            throw new UnauthorizedActionException();
        }

        Optional<Author> result = repository.findById(id);

        if(result.isEmpty()) {
            return null;
        }

        Author topic = result.get();

        topic.setName(entity.getName());

        return repository.save(topic);
    }

    public Author remove(User requestor, String id) {
        if (!requestor.hasRole(Set.of(UserRole.ADMIN, UserRole.LIBRARIAN))) {
            throw new UnauthorizedActionException();
        }

        Optional<Author> topic = repository.findById(id);

        if(!topic.isEmpty()) {
            repository.delete(topic.get());
        }

        return topic.orElse(null);
    }
}
