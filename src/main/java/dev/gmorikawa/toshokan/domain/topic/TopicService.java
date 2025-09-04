package dev.gmorikawa.toshokan.domain.topic;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.auth.exception.UnauthorizedActionException;
import dev.gmorikawa.toshokan.user.User;
import dev.gmorikawa.toshokan.user.enumerator.UserRole;

@Service
public class TopicService {

    private final TopicRepository repository;

    public TopicService(TopicRepository repository) {
        this.repository = repository;
    }

    public List<Topic> getAll() {
        return repository.findAll();
    }

    public Topic getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Topic getByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    public Topic create(User requestor, Topic entity) {
        if (!requestor.hasRole(Set.of(UserRole.ADMIN, UserRole.LIBRARIAN))) {
            throw new UnauthorizedActionException();
        }

        return repository.save(entity);
    }

    public Topic update(User requestor, String id, Topic entity) {
        if (!requestor.hasRole(Set.of(UserRole.ADMIN, UserRole.LIBRARIAN))) {
            throw new UnauthorizedActionException();
        }

        Optional<Topic> result = repository.findById(id);

        if(result.isEmpty()) {
            return null;
        }

        Topic topic = result.get();

        topic.setName(entity.getName());

        return repository.save(topic);
    }

    public Topic remove(User requestor, String id) {
        if (!requestor.hasRole(Set.of(UserRole.ADMIN, UserRole.LIBRARIAN))) {
            throw new UnauthorizedActionException();
        }

        Optional<Topic> topic = repository.findById(id);

        if(!topic.isEmpty()) {
            repository.delete(topic.get());
        }

        return topic.orElse(null);
    }
}
