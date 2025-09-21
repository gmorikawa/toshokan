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

import dev.gmorikawa.toshokan.domain.topic.Topic;
import dev.gmorikawa.toshokan.domain.topic.TopicService;
import dev.gmorikawa.toshokan.user.User;

@RestController("api.topic")
@RequestMapping(path = "api/topics")
public class TopicController {

    private final TopicService service;

    public TopicController(TopicService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Topic> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Topic getById(
        @PathVariable String id
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
        @RequestAttribute("user") User requestor,
        @RequestBody Topic topic
    ) {
        return service.create(topic);
    }

    @PatchMapping("/{id}")
    public Topic update(
        @RequestAttribute("user") User requestor,
        @PathVariable String id,
        @RequestBody Topic topic
    ) {
        return service.update(id, topic);
    }

    @DeleteMapping("/{id}")
    public Topic remove(
        @RequestAttribute("user") User requestor,
        @PathVariable String id
    ) {
        return service.remove(id);
    }
}