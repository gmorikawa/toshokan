package dev.gmorikawa.toshokan.topic;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class TopicService {
    private final TopicRepository repository;

    public TopicService(TopicRepository repository) {
        this.repository = repository;
    }

    public List<Topic> getAll() {
        return repository.findAll();
    }

    public Topic getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Topic insert(Topic entity) {
        return repository.save(entity);
    }

    public Topic update(String id, Topic entity) {
        Optional<Topic> result = repository.findById(id);

        if(result.isEmpty()) {
            return null;
        }

        Topic topic = result.get();

        topic.setName(entity.getName());

        return repository.save(topic);
    }

    public Topic remove(String id) {
        Optional<Topic> topic = repository.findById(id);

        if(!topic.isEmpty()) {
            repository.delete(topic.get());
        }

        return topic.orElse(null);
    }
}
