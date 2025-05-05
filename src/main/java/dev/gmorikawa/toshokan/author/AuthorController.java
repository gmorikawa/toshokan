package dev.gmorikawa.toshokan.author;

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
@RequestMapping(path = "api/authors")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Author> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Author getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping()
    public Author create(@RequestBody Author author) {
        return service.insert(author);
    }

    @PatchMapping("/{id}")
    public Author update(@PathVariable String id, @RequestBody Author author) {
        return service.update(id, author);
    }

    @DeleteMapping("/{id}")
    public Author remove(@PathVariable String id) {
        return service.remove(id);
    }
}