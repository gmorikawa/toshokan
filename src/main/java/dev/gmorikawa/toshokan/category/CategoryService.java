package dev.gmorikawa.toshokan.category;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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

    public Category insert(Category entity) {
        return repository.save(entity);
    }

    public Category update(String id, Category entity) {
        Optional<Category> result = repository.findById(id);

        if(result.isEmpty()) {
            return null;
        }

        Category category = result.get();

        category.setName(entity.getName());

        return repository.save(category);
    }

    public Category remove(String id) {
        Optional<Category> category = repository.findById(id);

        if(!category.isEmpty()) {
            repository.delete(category.get());
        }

        return category.orElse(null);
    }
}
