package dev.gmorikawa.toshokan.app.rest.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.gmorikawa.toshokan.domain.author.Author;
import dev.gmorikawa.toshokan.domain.author.AuthorService;
import dev.gmorikawa.toshokan.domain.user.User;
import dev.gmorikawa.toshokan.shared.query.Pagination;

@RestController("api.author")
@RequestMapping(path = "api/authors")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Author> getAll(
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "0") Integer size
    ) {
        if (page == 0 && size == 0) {
            return service.getAll();
        }
        Pagination pagination = new Pagination(page, size);
        return service.getAll(pagination);
    }

    @GetMapping("/count")
    public Integer countAll() {
        return service.countAll();
    }

    @GetMapping("/{id}")
    public Author getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping()
    public Author create(
        @RequestAttribute User user,
        @RequestBody Author author
    ) {
        return service.create(user, author);
    }

    @PatchMapping("/{id}")
    public Author update(
        @RequestAttribute User user,
        @PathVariable UUID id,
        @RequestBody Author author
    ) {
        return service.update(user, id, author);
    }

    @DeleteMapping("/{id}")
    public void remove(
        @RequestAttribute User user,
        @PathVariable UUID id
    ) {
        service.remove(user, id);
    }
}