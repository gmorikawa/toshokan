package dev.gmorikawa.toshokan.publisher;

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
    public Publisher getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping()
    public Publisher create(@RequestBody Publisher publisher) {
        return service.insert(publisher);
    }

    @PatchMapping("/{id}")
    public Publisher update(@PathVariable String id, @RequestBody Publisher publisher) {
        return service.update(id, publisher);
    }

    @DeleteMapping("/{id}")
    public Publisher remove(@PathVariable String id) {
        return service.remove(id);
    }
}