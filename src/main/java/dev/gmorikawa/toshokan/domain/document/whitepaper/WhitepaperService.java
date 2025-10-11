package dev.gmorikawa.toshokan.domain.document.whitepaper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.shared.query.Pagination;

@Service
public class WhitepaperService {

    private final WhitepaperRepository repository;

    public WhitepaperService(WhitepaperRepository repository) {
        this.repository = repository;
    }

    public List<Whitepaper> getAll(Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.page - 1, pagination.size);
        Page<Whitepaper> page = repository.findAll(pageable);

        return page.getContent();
    }

    public List<Whitepaper> getAll() {
        return repository.findAll();
    }

    public Whitepaper getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public Whitepaper create(Whitepaper entity) {
        return repository.save(entity);
    }

    public Whitepaper update(UUID id, Whitepaper entity) {
        Optional<Whitepaper> result = repository.findById(id);

        if (result.isEmpty()) {
            return null;
        }

        Whitepaper whitepaper = result.get();

        whitepaper.setTitle(entity.getTitle());
        // whitepaper.setYear(entity.getYear());
        whitepaper.setAuthors(entity.getAuthors());
        whitepaper.setDescription(entity.getDescription());
        whitepaper.setCategory(entity.getCategory());
        whitepaper.setTopics(entity.getTopics());

        return repository.save(whitepaper);
    }

    public boolean remove(UUID id) {
        Optional<Whitepaper> whitepaper = repository.findById(id);

        if (whitepaper.isEmpty()) {
            return false;
        }

        repository.delete(whitepaper.get());
        return true;
    }
}
