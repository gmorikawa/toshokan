package dev.gmorikawa.toshokan.domain.topic;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.auth.Authorization;
import dev.gmorikawa.toshokan.domain.user.User;
import dev.gmorikawa.toshokan.domain.user.enumerator.UserRole;
import dev.gmorikawa.toshokan.shared.query.Pagination;

@Service
public class TopicService {

    private final Authorization authorization;
    private final TopicRepository repository;

    public TopicService(
            Authorization authorization,
            TopicRepository repository
    ) {
        this.authorization = authorization;
        this.repository = repository;
    }

    public List<Topic> getAll(Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.page - 1, pagination.size);
        Page<Topic> page = repository.findAll(pageable);

        return page.getContent();
    }

    public List<Topic> getAll() {
        return repository.findAll();
    }

    public Topic getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public Topic getByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    public Topic create(User user, Topic entity) {
        authorization.checkUserRole(user, UserRole.ADMIN, UserRole.LIBRARIAN);

        return repository.save(entity);
    }

    public Topic update(User user, UUID id, Topic entity) {
        authorization.checkUserRole(user, UserRole.ADMIN, UserRole.LIBRARIAN);
        Optional<Topic> result = repository.findById(id);

        if (result.isEmpty()) {
            return null;
        }

        Topic topic = result.get();

        topic.setName(entity.getName());

        return repository.save(topic);
    }

    public Topic remove(User user, UUID id) {
        authorization.checkUserRole(user, UserRole.ADMIN, UserRole.LIBRARIAN);

        Optional<Topic> topic = repository.findById(id);

        if (!topic.isEmpty()) {
            repository.delete(topic.get());
        }

        return topic.orElse(null);
    }
}
