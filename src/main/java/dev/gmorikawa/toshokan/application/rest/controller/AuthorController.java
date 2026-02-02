package dev.gmorikawa.toshokan.application.rest.controller;

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

import dev.gmorikawa.toshokan.core.author.Author;
import dev.gmorikawa.toshokan.core.author.AuthorQueryFilter;
import dev.gmorikawa.toshokan.core.author.AuthorService;
import dev.gmorikawa.toshokan.core.user.entity.LoggedUser;
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
        @RequestAttribute(required = false) Pagination pagination,
        @RequestParam(required = false) List<String> fullname
    ) {
        if (pagination == null) {
            if (fullname == null) {
                return service.getAll();
            } else {
                AuthorQueryFilter filter = new AuthorQueryFilter(fullname);
                return service.searchByFullname(filter);
            }
        }

        if (fullname != null && !fullname.isEmpty()) {
            AuthorQueryFilter filter = new AuthorQueryFilter(fullname);
            return service.searchByFullname(filter, pagination);
        } else {
            return service.getAll(pagination);
        }
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
        @RequestAttribute LoggedUser loggedUser,
        @RequestBody Author author
    ) {
        return service.create(loggedUser, author);
    }

    @PatchMapping("/{id}")
    public Author update(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id,
        @RequestBody Author author
    ) {
        return service.update(loggedUser, id, author);
    }

    @DeleteMapping("/{id}")
    public void remove(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id
    ) {
        service.remove(loggedUser, id);
    }
}