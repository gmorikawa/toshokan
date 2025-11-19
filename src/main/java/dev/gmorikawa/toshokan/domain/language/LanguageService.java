package dev.gmorikawa.toshokan.domain.language;

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
public class LanguageService {

    private final Authorization authorization;
    private final LanguageRepository repository;

    public LanguageService(
            Authorization authorization,
            LanguageRepository repository
    ) {
        this.authorization = authorization;
        this.repository = repository;
    }

    public List<Language> getAll(Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.page - 1, pagination.size);
        Page<Language> page = repository.findAll(pageable);

        return page.getContent();
    }

    public Integer countAll() {
        return (int) repository.count();
    }

    public List<Language> getAll() {
        return repository.findAll();
    }

    public Language getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public Language getByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    public Language create(User user, Language entity) {
        authorization.checkUserRole(user, UserRole.ADMIN, UserRole.LIBRARIAN);

        return repository.save(entity);
    }

    public Language update(User user, UUID id, Language entity) {
        authorization.checkUserRole(user, UserRole.ADMIN, UserRole.LIBRARIAN);
        Optional<Language> result = repository.findById(id);

        if (result.isEmpty()) {
            return null;
        }

        Language language = result.get();

        language.setName(entity.getName());

        return repository.save(language);
    }

    public Language remove(User user, UUID id) {
        authorization.checkUserRole(user, UserRole.ADMIN, UserRole.LIBRARIAN);

        Optional<Language> language = repository.findById(id);

        if (!language.isEmpty()) {
            repository.delete(language.get());
        }

        return language.orElse(null);
    }
}
