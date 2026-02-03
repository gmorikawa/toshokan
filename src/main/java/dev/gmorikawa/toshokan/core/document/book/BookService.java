package dev.gmorikawa.toshokan.core.document.book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.core.auth.contract.Authorization;
import dev.gmorikawa.toshokan.core.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.core.user.enumerator.UserRole;
import dev.gmorikawa.toshokan.shared.query.Pagination;

@Service
public class BookService {

    private final Authorization authorization;
    private final BookRepository repository;

    public BookService(
            Authorization authorization,
            BookRepository repository
    ) {
        this.authorization = authorization;
        this.repository = repository;
    }

    public List<Book> searchByTitle(BookQueryFilter filter) {
        return repository
            .searchByTitle(
                filter.getTitle().getValue()
            );
    }

    public List<Book> searchByTitle(BookQueryFilter filter, Pagination pagination) {
        Page<Book> page = repository
            .searchByTitle(
                filter.getTitle().getValue(),
                PageRequest.of(pagination.page - 1, pagination.limit)
            );

        return page.getContent();
    }

    public List<Book> getAll(Pagination pagination) {
        Page<Book> page = repository
            .findAll(PageRequest.of(pagination.page - 1, pagination.limit));

        return page.getContent();
    }

    public Integer countAll() {
        return (int) repository.count();
    }

    public List<Book> getAll() {
        return repository.findAll();
    }

    public Book getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public Book create(LoggedUser loggedUser, Book entity) {
        authorization.checkUserRole(loggedUser, UserRole.ADMIN, UserRole.LIBRARIAN);

        return repository.save(entity);
    }

    public Book update(LoggedUser loggedUser, UUID id, Book entity) {
        authorization.checkUserRole(loggedUser, UserRole.ADMIN, UserRole.LIBRARIAN);

        Optional<Book> result = repository.findById(id);

        if (result.isEmpty()) {
            return null;
        }

        Book book = result.get();

        book.setTitle(entity.getTitle());
        book.setSubtitle(entity.getSubtitle());
        book.setAuthors(entity.getAuthors());
        book.setSummary(entity.getSummary());
        book.setCategory(entity.getCategory());
        book.setPublisher(entity.getPublisher());
        book.setLanguage(entity.getLanguage());
        book.setTopics(entity.getTopics());
        book.setType(entity.getType());
        book.setPublishingYear(entity.getPublishingYear());
        book.setEdition(entity.getEdition());

        return repository.save(book);
    }

    public boolean remove(LoggedUser loggedUser, UUID id) {
        authorization.checkUserRole(loggedUser, UserRole.ADMIN, UserRole.LIBRARIAN);

        Optional<Book> book = repository.findById(id);

        if (!book.isEmpty()) {
            repository.delete(book.get());
            return true;
        }

        return false;
    }
}
