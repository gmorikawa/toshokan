package dev.gmorikawa.toshokan.publisher;

import java.util.List;

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
        return repository.getReferenceById(id);
    }

    public Publisher insert(Publisher entity) {
        return repository.save(entity);
    }

    public Publisher update(String id, Publisher entity) {
        Publisher current = getById(id);

        if(current != null) {
            current.setName(entity.getName());
        }

        return repository.save(current);
    }

    public boolean remove(String id) {
        Publisher entity = getById(id);
        repository.delete(entity);

        return true;
    }
}
