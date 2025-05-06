package dev.gmorikawa.toshokan.category;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.auth.exception.UnauthorizedActionException;
import dev.gmorikawa.toshokan.user.User;
import dev.gmorikawa.toshokan.user.enumerator.UserRole;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Category getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Category getByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    public Category create(User requestor, Category entity) {
        if (!requestor.hasRole(Set.of(UserRole.ADMIN, UserRole.LIBRARIAN))) {
            throw new UnauthorizedActionException();
        }

        return repository.save(entity);
    }

    public Category update(User requestor, String id, Category entity) {
        if (!requestor.hasRole(Set.of(UserRole.ADMIN, UserRole.LIBRARIAN))) {
            throw new UnauthorizedActionException();
        }

        Optional<Category> result = repository.findById(id);

        if(result.isEmpty()) {
            return null;
        }

        Category category = result.get();

        category.setName(entity.getName());

        return repository.save(category);
    }

    public Category remove(User requestor, String id) {
        if (!requestor.hasRole(Set.of(UserRole.ADMIN, UserRole.LIBRARIAN))) {
            throw new UnauthorizedActionException();
        }

        Optional<Category> category = repository.findById(id);

        if(!category.isEmpty()) {
            repository.delete(category.get());
        }

        return category.orElse(null);
    }
}
