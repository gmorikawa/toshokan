package dev.gmorikawa.toshokan.domain.category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.domain.auth.contract.Authorization;
import dev.gmorikawa.toshokan.domain.category.exception.CategoryNameNotAvailableException;
import dev.gmorikawa.toshokan.domain.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.domain.user.enumerator.UserRole;
import dev.gmorikawa.toshokan.shared.query.Pagination;

@Service
public class CategoryService {

    private final Authorization authorization;
    private final CategoryRepository repository;

    public CategoryService(
        Authorization authorization,
        CategoryRepository repository
    ) {
        this.authorization = authorization;
        this.repository = repository;
    }

    public List<Category> getAll() {
        return repository.findAll();
    }

    public List<Category> getAll(Pagination pagination) {
        Page<Category> page = repository.findAll(
            PageRequest.of(pagination.page - 1, pagination.limit)
        );
        
        return page.getContent();
    }

    public Integer countAll() {
        return (int) repository.count();
    }

    public Category getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public Category getByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    public Category create(LoggedUser loggedUser, Category entity) {
        authorization.checkUserRole(loggedUser, UserRole.ADMIN, UserRole.LIBRARIAN);

        if (!isNameAvailable(entity.getName())) {
            throw new CategoryNameNotAvailableException();
        }

        return repository.save(entity);
    }

    public Category update(LoggedUser loggedUser, UUID id, Category entity) {
        authorization.checkUserRole(loggedUser, UserRole.ADMIN, UserRole.LIBRARIAN);

        if (!isNameAvailable(entity.getName(), id)) {
            throw new CategoryNameNotAvailableException();
        }

        Optional<Category> result = repository.findById(id);

        if (result.isEmpty()) {
            return null;
        }

        Category category = result.get();

        category.setName(entity.getName());

        return repository.save(category);
    }

    public void remove(LoggedUser loggedUser, UUID id) {
        authorization.checkUserRole(loggedUser, UserRole.ADMIN, UserRole.LIBRARIAN);

        Optional<Category> category = repository.findById(id);

        if (!category.isEmpty()) {
            repository.delete(category.get());
        }
    }

    public boolean isNameAvailable(String name) {
        return repository.findByName(name).isEmpty();
    }

    public boolean isNameAvailable(String name, UUID ignoreId) {
        return repository.findByName(name, ignoreId).isEmpty();
    }
}
