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

import dev.gmorikawa.toshokan.core.topic.Topic;
import dev.gmorikawa.toshokan.core.topic.TopicQueryFilter;
import dev.gmorikawa.toshokan.core.topic.TopicService;
import dev.gmorikawa.toshokan.core.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.shared.query.Pagination;

@RestController("api.topic")
@RequestMapping(path = "api/topics")
public class TopicController {

    private final TopicService service;

    public TopicController(TopicService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Topic> getAll(
        @RequestAttribute(required = false) Pagination pagination,
        @RequestParam(required = false, name = "contains_name") String name
    ) {
        if (pagination == null) {
            if (name == null) {
                return service.getAll();
            } else {
                TopicQueryFilter filter = new TopicQueryFilter(name);
                return service.searchByName(filter);
            }
        }

        if (name != null && !name.isEmpty()) {
            TopicQueryFilter filter = new TopicQueryFilter(name);
            return service.searchByName(filter, pagination);
        } else {
            return service.getAll(pagination);
        }
    }

    @GetMapping("/count")
    public Integer countAll() {
        return service.countAll();
    }

    @GetMapping("/{id}")
    public Topic getById(
        @PathVariable UUID id
    ) {
        return service.getById(id);
    }

    @GetMapping("/name/{name}")
    public Topic getByName(
        @PathVariable String name
    ) {
        return service.getByName(name);
    }

    @PostMapping()
    public Topic create(
        @RequestAttribute LoggedUser loggedUser,
        @RequestBody Topic topic
    ) {
        return service.create(loggedUser, topic);
    }

    @PatchMapping("/{id}")
    public Topic update(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id,
        @RequestBody Topic topic
    ) {
        return service.update(loggedUser, id, topic);
    }

    @DeleteMapping("/{id}")
    public Topic remove(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id
    ) {
        return service.remove(loggedUser, id);
    }
}