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

import dev.gmorikawa.toshokan.domain.publisher.Publisher;
import dev.gmorikawa.toshokan.domain.publisher.PublisherService;
import dev.gmorikawa.toshokan.domain.user.User;
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
        @RequestAttribute User user,
        @RequestBody Publisher publisher
    ) {
        return service.create(user, publisher);
    }

    @PatchMapping("/{id}")
    public Publisher update(
        @RequestAttribute User user,
        @PathVariable UUID id,
        @RequestBody Publisher publisher
    ) {
        return service.update(user, id, publisher);
    }

    @DeleteMapping("/{id}")
    public Publisher remove(
        @RequestAttribute User user,
        @PathVariable UUID id
    ) {
        return service.remove(user, id);
    }
}