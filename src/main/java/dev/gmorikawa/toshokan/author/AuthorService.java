package dev.gmorikawa.toshokan.author;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public List<Author> getAll() {
        return repository.findAll();
    }

    public Author getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Author insert(Author entity) {
        return repository.save(entity);
    }

    public Author update(String id, Author entity) {
        Optional<Author> result = repository.findById(id);

        if(result.isEmpty()) {
            return null;
        }

        Author topic = result.get();

        topic.setName(entity.getName());

        return repository.save(topic);
    }

    public Author remove(String id) {
        Optional<Author> topic = repository.findById(id);

        if(!topic.isEmpty()) {
            repository.delete(topic.get());
        }

        return topic.orElse(null);
    }
}
