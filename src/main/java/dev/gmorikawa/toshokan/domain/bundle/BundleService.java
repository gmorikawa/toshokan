package dev.gmorikawa.toshokan.domain.bundle;

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
public class BundleService {
    
    private final Authorization authorization;
    private final BundleRepository repository;

    public BundleService(
        Authorization authorization,
        BundleRepository repository
    ) {
        this.authorization = authorization;
        this.repository = repository;
    }

    public List<Bundle> getAll() {
        return repository.findAll();
    }

    public List<Bundle> getAll(Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.page - 1, pagination.size);
        Page<Bundle> page = repository.findAll(pageable);
        
        return page.getContent();
    }

    public Integer countAll() {
        return (int) repository.count();
    }

    public Bundle getByTitle(String title) {
        return repository.findByTitle(title).orElse(null);
    }

    public Bundle getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public Bundle create(LoggedUser loggedUser, Bundle entity) {
        authorization.checkUserRole(loggedUser, UserRole.ADMIN, UserRole.LIBRARIAN);

        return repository.save(entity);
    }

    public Bundle update(LoggedUser loggedUser, UUID id, Bundle entity) {
        authorization.checkUserRole(loggedUser, UserRole.ADMIN, UserRole.LIBRARIAN);

        Optional<Bundle> result = repository.findById(id);

        if (result.isEmpty()) {
            return null;
        }

        Bundle bundle = result.get();

        bundle.setTitle(entity.getTitle());
        bundle.setDescription(entity.getDescription());

        return repository.save(bundle);
    }

    public void remove(LoggedUser loggedUser, UUID id) {
        authorization.checkUserRole(loggedUser, UserRole.ADMIN, UserRole.LIBRARIAN);

        Optional<Bundle> bundle = repository.findById(id);

        if (!bundle.isEmpty()) {
            repository.delete(bundle.get());
        }
    }
}