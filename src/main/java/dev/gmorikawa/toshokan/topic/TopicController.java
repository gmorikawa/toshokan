package dev.gmorikawa.toshokan.topic;

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
    public Topic getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping()
    public Topic create(@RequestBody Topic topic) {
        return service.insert(topic);
    }

    @PatchMapping("/{id}")
    public Topic update(@PathVariable String id, @RequestBody Topic topic) {
        return service.update(id, topic);
    }

    @DeleteMapping("/{id}")
    public Topic remove(@PathVariable String id) {
        return service.remove(id);
    }
}