package dev.gmorikawa.toshokan.domain.document.whitepaper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.domain.auth.contract.Authorization;
import dev.gmorikawa.toshokan.domain.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.domain.user.enumerator.UserRole;
import dev.gmorikawa.toshokan.shared.query.Pagination;

@Service
public class WhitepaperService {

    private final Authorization authorization;
    private final WhitepaperRepository repository;

    public WhitepaperService(
            Authorization authorization,
            WhitepaperRepository repository
    ) {
        this.authorization = authorization;
        this.repository = repository;
    }

    public List<Whitepaper> searchByTitle(String query, Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.page - 1, pagination.limit);
        Page<Whitepaper> page = repository.searchByTitle(query, pageable);
        return page.getContent();
    }

    public List<Whitepaper> searchByTitle(String query) {
        return repository.searchByTitle(query);
    }

    public List<Whitepaper> getAll(Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.page - 1, pagination.limit);
        Page<Whitepaper> page = repository.findAll(pageable);

        return page.getContent();
    }

    public Integer countAll() {
        return (int) repository.count();
    }

    public List<Whitepaper> getAll() {
        return repository.findAll();
    }

    public Whitepaper getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public Whitepaper create(LoggedUser loggedUser, Whitepaper entity) {
        authorization.checkUserRole(loggedUser, UserRole.ADMIN, UserRole.LIBRARIAN);

        return repository.save(entity);
    }

    public Whitepaper update(LoggedUser loggedUser, UUID id, Whitepaper entity) {
        authorization.checkUserRole(loggedUser, UserRole.ADMIN, UserRole.LIBRARIAN);

        Optional<Whitepaper> result = repository.findById(id);

        if (result.isEmpty()) {
            return null;
        }

        Whitepaper whitepaper = result.get();

        whitepaper.setTitle(entity.getTitle());
        whitepaper.setAuthors(entity.getAuthors());
        whitepaper.setSummary(entity.getSummary());
        whitepaper.setTopics(entity.getTopics());
        whitepaper.setLanguage(entity.getLanguage());
        whitepaper.setOrganization(entity.getOrganization());
        whitepaper.setPublishingYear(entity.getPublishingYear());
        return repository.save(whitepaper);
    }

    public boolean remove(LoggedUser loggedUser, UUID id) {
        authorization.checkUserRole(loggedUser, UserRole.ADMIN, UserRole.LIBRARIAN);

        Optional<Whitepaper> whitepaper = repository.findById(id);

        if (whitepaper.isEmpty()) {
            return false;
        }

        repository.delete(whitepaper.get());
        return true;
    }
}
