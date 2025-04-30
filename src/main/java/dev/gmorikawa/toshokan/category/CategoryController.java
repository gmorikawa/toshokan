package dev.gmorikawa.toshokan.category;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

    @PostMapping()
    public Category create(@RequestBody Category category) {
        return service.insert(category);
    }

    @PatchMapping("/{id}")
    public Category update(@PathVariable String id, @RequestBody Category category) {
        return service.update(id, category);
    }

    @DeleteMapping("/{id}")
    public Category remove(@PathVariable String id) {
        return service.remove(id);
    }

}