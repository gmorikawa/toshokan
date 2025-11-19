package dev.gmorikawa.toshokan.domain.document.researchpaper;

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
public class ResearchPaperService {

    private final Authorization authorization;
    private final ResearchPaperRepository repository;

    public ResearchPaperService(
            Authorization authorization,
            ResearchPaperRepository repository
    ) {
        this.authorization = authorization;
        this.repository = repository;
    }

    public List<ResearchPaper> getAll(Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.page - 1, pagination.size);
        Page<ResearchPaper> page = repository.findAll(pageable);

        return page.getContent();
    }

    public Integer countAll() {
        return (int) repository.count();
    }

    public List<ResearchPaper> getAll() {
        return repository.findAll();
    }

    public ResearchPaper getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public ResearchPaper create(User user, ResearchPaper entity) {
        authorization.checkUserRole(user, UserRole.ADMIN, UserRole.LIBRARIAN);

        return repository.save(entity);
    }

    public ResearchPaper update(User user, UUID id, ResearchPaper entity) {
        authorization.checkUserRole(user, UserRole.ADMIN, UserRole.LIBRARIAN);

        Optional<ResearchPaper> result = repository.findById(id);

        if (result.isEmpty()) {
            return null;
        }

        ResearchPaper researchPaper = result.get();

        researchPaper.setTitle(entity.getTitle());
        researchPaper.setAuthors(entity.getAuthors());
        researchPaper.setSummary(entity.getSummary());
        researchPaper.setTopics(entity.getTopics());
        researchPaper.setOrganization(entity.getOrganization());
        researchPaper.setKeywords(entity.getKeywords());

        return repository.save(researchPaper);
    }

    public boolean remove(User user, UUID id) {
        authorization.checkUserRole(user, UserRole.ADMIN, UserRole.LIBRARIAN);

        Optional<ResearchPaper> researchPaper = repository.findById(id);

        if (researchPaper.isEmpty()) {
            return false;
        }

        repository.delete(researchPaper.get());
        return true;
    }
}
