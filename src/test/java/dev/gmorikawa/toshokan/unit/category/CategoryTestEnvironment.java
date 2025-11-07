package dev.gmorikawa.toshokan.unit.category;

import org.springframework.beans.factory.annotation.Autowired;

import dev.gmorikawa.toshokan.domain.category.Category;
import dev.gmorikawa.toshokan.domain.category.CategoryRepository;
import dev.gmorikawa.toshokan.domain.category.CategoryService;

public abstract class CategoryTestEnvironment {

    @Autowired
    protected CategoryRepository repository;

    @Autowired
    protected CategoryService service;

    protected void clean(Category category) {
        repository.delete(category);
    }

}
