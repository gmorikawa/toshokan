package dev.gmorikawa.toshokan.category;

import java.util.List;

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
        return repository.getReferenceById(id);
    }

    public Category insert(Category entity) {
        return repository.save(entity);
    }

    public Category update(String id, Category entity) {
        Category current = getById(id);

        if(current != null) {
            current.setName(entity.getName());
        }

        return repository.save(current);
    }

    public boolean remove(String id) {
        Category entity = getById(id);
        repository.delete(entity);

        return true;
    }
}
