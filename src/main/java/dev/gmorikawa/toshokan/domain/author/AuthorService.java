package dev.gmorikawa.toshokan.domain.author;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.domain.auth.contract.Authorization;
import dev.gmorikawa.toshokan.domain.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.domain.user.enumerator.UserRole;
import dev.gmorikawa.toshokan.shared.query.Pagination;

@Service
public class AuthorService {

    private final Authorization authorization;
    private final AuthorRepository repository;

    public AuthorService(
        Authorization authorization,
        AuthorRepository repository
    ) {
        this.authorization = authorization;
        this.repository = repository;
    }

    public List<Author> searchByFullname(AuthorQueryFilter filter) {
        return repository
            .searchByFullname(
                filter.getFullname().getValue()
            );
    }

    public List<Author> searchByFullname(AuthorQueryFilter filter, Pagination pagination) {
        Page<Author> page = repository
            .searchByFullname(
                filter.getFullname().getValue(),
                PageRequest.of(pagination.page - 1, pagination.limit)
            );

        return page.getContent();
    }

    public List<Author> getAll(Pagination pagination) {
        Page<Author> page = repository
            .findAll(PageRequest.of(pagination.page - 1, pagination.limit));
        
        return page.getContent();
    }

    public Integer countAll() {
        return (int) repository.count();
    }

    public List<Author> getAll() {
        return repository.findAll();
    }

    public Author getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public Author getByName(String fullname) {
        return repository.findByFullname(fullname).orElse(null);
    }

    public Author create(LoggedUser loggedUser, Author entity) {
        authorization.checkUserRole(loggedUser, UserRole.ADMIN, UserRole.LIBRARIAN);

        return repository.save(entity);
    }

    public Author update(LoggedUser loggedUser, UUID id, Author entity) {
        authorization.checkUserRole(loggedUser, UserRole.ADMIN, UserRole.LIBRARIAN);

        Optional<Author> result = repository.findById(id);

        if (result.isEmpty()) {
            return null;
        }

        Author author = result.get();

        author.setFullname(entity.getFullname());
        author.setBiography(entity.getBiography());

        return repository.save(author);
    }

    public void remove(LoggedUser loggedUser, UUID id) {
        authorization.checkUserRole(loggedUser, UserRole.ADMIN, UserRole.LIBRARIAN);

        Optional<Author> author = repository.findById(id);

        if (!author.isEmpty()) {
            repository.delete(author.get());
        }
    }
}
