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

import dev.gmorikawa.toshokan.domain.publisher.Publisher;
import dev.gmorikawa.toshokan.domain.publisher.PublisherService;
import dev.gmorikawa.toshokan.user.User;

@RestController
@RequestMapping(path = "api/publishers")
public class PublisherController {

    private final PublisherService service;

    public PublisherController(PublisherService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Publisher> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Publisher getById(
        @PathVariable String id
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
        @RequestAttribute("user") User requestor,
        @RequestBody Publisher publisher
    ) {
        return service.create(publisher);
    }

    @PatchMapping("/{id}")
    public Publisher update(
        @RequestAttribute("user") User requestor,
        @PathVariable String id,
        @RequestBody Publisher publisher
    ) {
        return service.update(id, publisher);
    }

    @DeleteMapping("/{id}")
    public Publisher remove(
        @RequestAttribute("user") User requestor,
        @PathVariable String id
    ) {
        return service.remove(id);
    }
}