package dev.gmorikawa.toshokan.core.topic;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.core.auth.contract.Authorization;
import dev.gmorikawa.toshokan.core.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.core.user.enumerator.UserRole;
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

    public List<Topic> searchByName(TopicQueryFilter filter, Pagination pagination) {
        Page<Topic> page = repository
            .searchByName(
                filter.getName().getValue(),
                PageRequest.of(pagination.page - 1, pagination.limit)
            );

        return page.getContent();
    }

    public List<Topic> searchByName(TopicQueryFilter filter) {
        return repository.searchByName(filter.getName().getValue());
    }

    public List<Topic> getAll(Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.page - 1, pagination.limit);
        Page<Topic> page = repository.findAll(pageable);

        return page.getContent();
    }

    public Integer countAll() {
        return (int) repository.count();
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

    public Topic create(LoggedUser loggedUser, Topic entity) {
        authorization.checkUserRole(loggedUser, UserRole.ADMIN, UserRole.LIBRARIAN);

        return repository.save(entity);
    }

    public Topic update(LoggedUser loggedUser, UUID id, Topic entity) {
        authorization.checkUserRole(loggedUser, UserRole.ADMIN, UserRole.LIBRARIAN);
        Optional<Topic> result = repository.findById(id);

        if (result.isEmpty()) {
            return null;
        }

        Topic topic = result.get();

        topic.setName(entity.getName());

        return repository.save(topic);
    }

    public Topic remove(LoggedUser loggedUser, UUID id) {
        authorization.checkUserRole(loggedUser, UserRole.ADMIN, UserRole.LIBRARIAN);

        Optional<Topic> topic = repository.findById(id);

        if (!topic.isEmpty()) {
            repository.delete(topic.get());
        }

        return topic.orElse(null);
    }
}
