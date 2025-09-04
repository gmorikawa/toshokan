package dev.gmorikawa.toshokan.domain.publisher;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.auth.exception.UnauthorizedActionException;
import dev.gmorikawa.toshokan.user.User;
import dev.gmorikawa.toshokan.user.enumerator.UserRole;

@Service
public class PublisherService {

    private final PublisherRepository repository;

    public PublisherService(PublisherRepository repository) {
        this.repository = repository;
    }

    public List<Publisher> getAll() {
        return repository.findAll();
    }

    public Publisher getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Publisher getByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    public Publisher create(User requestor, Publisher entity) {
        if (!requestor.hasRole(Set.of(UserRole.ADMIN, UserRole.LIBRARIAN))) {
            throw new UnauthorizedActionException();
        }

        return repository.save(entity);
    }

    public Publisher update(User requestor, String id, Publisher entity) {
        if (!requestor.hasRole(Set.of(UserRole.ADMIN, UserRole.LIBRARIAN))) {
            throw new UnauthorizedActionException();
        }

        Optional<Publisher> result = repository.findById(id);

        if(result.isEmpty()) {
            return null;
        }

        Publisher publisher = result.get();

        publisher.setName(entity.getName());
        publisher.setDescription(entity.getDescription());

        return repository.save(publisher);
    }

    public Publisher remove(User requestor, String id) {
        if (!requestor.hasRole(Set.of(UserRole.ADMIN, UserRole.LIBRARIAN))) {
            throw new UnauthorizedActionException();
        }

        Optional<Publisher> publisher = repository.findById(id);

        if(!publisher.isEmpty()) {
            repository.delete(publisher.get());
        }

        return publisher.orElse(null);
    }
}
