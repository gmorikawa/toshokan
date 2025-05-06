package dev.gmorikawa.toshokan.document.whitepaper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.auth.exception.UnauthorizedActionException;
import dev.gmorikawa.toshokan.user.User;
import dev.gmorikawa.toshokan.user.enumerator.UserRole;

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

    public List<Whitepaper> getByYear(Integer year) {
        return repository.findByYear(year);
    }

    public Whitepaper create(User requestor, Whitepaper entity) {
        if (!requestor.hasRole(Set.of(UserRole.ADMIN, UserRole.LIBRARIAN))) {
            throw new UnauthorizedActionException();
        }

        return repository.save(entity);
    }

    public Whitepaper update(User requestor, String id, Whitepaper entity) {
        if (!requestor.hasRole(Set.of(UserRole.ADMIN, UserRole.LIBRARIAN))) {
            throw new UnauthorizedActionException();
        }

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

    public boolean remove(User requestor, String id) {
        if (!requestor.hasRole(Set.of(UserRole.ADMIN, UserRole.LIBRARIAN))) {
            throw new UnauthorizedActionException();
        }

        Optional<Whitepaper> whitepaper = repository.findById(id);

        if (whitepaper.isEmpty()) {
            return false;
        }

        repository.delete(whitepaper.get());
        return true;
    }
}
