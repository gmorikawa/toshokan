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
import org.springframework.web.bind.annotation.RestController;

import dev.gmorikawa.toshokan.domain.publisher.Publisher;
import dev.gmorikawa.toshokan.domain.publisher.PublisherService;
import dev.gmorikawa.toshokan.domain.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.shared.query.Pagination;

@RestController("api.publisher")
@RequestMapping(path = "api/publishers")
public class PublisherController {

    private final PublisherService service;

    public PublisherController(PublisherService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Publisher> getAll(
        @RequestAttribute(required = false) Pagination pagination
    ) {
        if (pagination == null) {
            return service.getAll();
        }

        return service.getAll(pagination);
    }

    @GetMapping("/count")
    public Integer countAll() {
        return service.countAll();
    }

    @GetMapping("/{id}")
    public Publisher getById(
        @PathVariable UUID id
    ) {
        return service.getById(id);
    }

    @GetMapping("/name/{name}")
    public Publisher getByName(
        @PathVariable String name
    ) {
        return service.getByName(name);
    }

    @PostMapping()
    public Publisher create(
        @RequestAttribute LoggedUser loggedUser,
        @RequestBody Publisher publisher
    ) {
        return service.create(loggedUser, publisher);
    }

    @PatchMapping("/{id}")
    public Publisher update(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id,
        @RequestBody Publisher publisher
    ) {
        return service.update(loggedUser, id, publisher);
    }

    @DeleteMapping("/{id}")
    public Publisher remove(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id
    ) {
        return service.remove(loggedUser, id);
    }
}