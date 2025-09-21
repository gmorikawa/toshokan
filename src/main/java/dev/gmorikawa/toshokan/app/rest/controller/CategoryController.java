package dev.gmorikawa.toshokan.app.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gmorikawa.toshokan.domain.category.Category;
import dev.gmorikawa.toshokan.domain.category.CategoryService;
import dev.gmorikawa.toshokan.domain.category.exception.CategoryNameNotAvailableException;
import dev.gmorikawa.toshokan.user.User;

@RestController("api.category")
@RequestMapping(path = "api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Category> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping("/name/{name}")
    public Category getByName(@PathVariable String name) {
        return service.getByName(name);
    }

    @PostMapping()
    public Category create(
        @RequestAttribute("user") User requestor,
        @RequestBody Category category
    ) throws CategoryNameNotAvailableException {
        return service.create(category);
    }

    @PatchMapping("/{id}")
    public Category update(
        @RequestAttribute("user") User requestor,
        @PathVariable String id,
        @RequestBody Category category
    ) throws CategoryNameNotAvailableException {
        return service.update(id, category);
    }

    @DeleteMapping("/{id}")
    public void remove(
        @RequestAttribute("user") User requestor,
        @PathVariable String id
    ) {
        service.remove(id);
    }

}