package dev.gmorikawa.toshokan.document.whitepaper;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class WhitepaperService {

    private final WhitepaperRepository repository;

    public WhitepaperService(WhitepaperRepository repository) {
        this.repository = repository;
    }

    public List<Whitepaper> getAll() {
        return repository.findAll();
    }

    public Whitepaper getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Whitepaper getByYear(Integer year) {
        return repository.findByYear(year).orElse(null);
    }

    public Whitepaper insert(Whitepaper entity) {
        return repository.save(entity);
    }

    public Whitepaper update(String id, Whitepaper entity) {
        Optional<Whitepaper> result = repository.findById(id);

        if (result.isEmpty()) {
            return null;
        }

        Whitepaper whitepaper = result.get();

        whitepaper.setTitle(entity.getTitle());
        whitepaper.setYear(entity.getYear());
        whitepaper.setAuthors(entity.getAuthors());
        whitepaper.setDescription(entity.getDescription());
        whitepaper.setCategory(entity.getCategory());
        whitepaper.setTopics(entity.getTopics());

        return repository.save(whitepaper);
    }

    public boolean remove(String id) {
        Optional<Whitepaper> whitepaper = repository.findById(id);

        if (whitepaper.isEmpty()) {
            return false;
        }

        repository.delete(whitepaper.get());
        return true;
    }
}
