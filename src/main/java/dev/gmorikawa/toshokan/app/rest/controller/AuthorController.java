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

import dev.gmorikawa.toshokan.domain.author.Author;
import dev.gmorikawa.toshokan.domain.author.AuthorService;
import dev.gmorikawa.toshokan.user.User;

@RestController("api.author")
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
    public Author create(
        @RequestAttribute("user") User requestor,
        @RequestBody Author author
    ) {
        return service.create(author);
    }

    @PatchMapping("/{id}")
    public Author update(
        @RequestAttribute("user") User requestor,
        @PathVariable String id,
        @RequestBody Author author
    ) {
        return service.update(id, author);
    }

    @DeleteMapping("/{id}")
    public void remove(
        @RequestAttribute("user") User requestor,
        @PathVariable String id
    ) {
        service.remove(id);
    }
}