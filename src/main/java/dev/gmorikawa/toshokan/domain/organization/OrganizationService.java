package dev.gmorikawa.toshokan.domain.organization;

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
public class OrganizationService {
    
    private final Authorization authorization;
    private final OrganizationRepository repository;

    public OrganizationService(
        Authorization authorization,
        OrganizationRepository repository
    ) {
        this.authorization = authorization;
        this.repository = repository;
    }

    public List<Organization> getAll(Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.page - 1, pagination.size);
        Page<Organization> page = repository.findAll(pageable);
        
        return page.getContent();
    }

    public List<Organization> getAll() {
        return repository.findAll();
    }

    public Organization getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public Organization getByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    public Organization create(User user, Organization entity) {
        authorization.checkUserRole(user, UserRole.ADMIN, UserRole.LIBRARIAN);

        return repository.save(entity);
    }

    public Organization update(User user, UUID id, Organization entity) {
        authorization.checkUserRole(user, UserRole.ADMIN, UserRole.LIBRARIAN);

        Optional<Organization> result = repository.findById(id);

        if(result.isEmpty()) {
            return null;
        }

        Organization organization = result.get();

        organization.setName(entity.getName());
        organization.setDescription(entity.getDescription());
        organization.setType(entity.getType());

        return repository.save(organization);
    }

    public Organization remove(User user, UUID id) {
        authorization.checkUserRole(user, UserRole.ADMIN, UserRole.LIBRARIAN);

        Optional<Organization> organization = repository.findById(id);

        if(!organization.isEmpty()) {
            repository.delete(organization.get());
        }

        return organization.orElse(null);
    }
}
