package dev.gmorikawa.toshokan.domain.publisher;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class PublisherService {

    private final PublisherRepository repository;

    public PublisherService(PublisherRepository repository) {
        this.repository = repository;
    }

    public List<Publisher> getAll() {
        return repository.findAll();
    }

    public Publisher getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Publisher getByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    public Publisher create(Publisher entity) {
        return repository.save(entity);
    }

    public Publisher update(String id, Publisher entity) {
        Optional<Publisher> result = repository.findById(id);

        if(result.isEmpty()) {
            return null;
        }

        Publisher publisher = result.get();

        publisher.setName(entity.getName());
        publisher.setDescription(entity.getDescription());

        return repository.save(publisher);
    }

    public Publisher remove(String id) {

        Optional<Publisher> publisher = repository.findById(id);

        if(!publisher.isEmpty()) {
            repository.delete(publisher.get());
        }

        return publisher.orElse(null);
    }
}
