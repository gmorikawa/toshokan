package dev.gmorikawa.toshokan.document.book;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.auth.exception.UnauthorizedActionException;
import dev.gmorikawa.toshokan.user.User;
import dev.gmorikawa.toshokan.user.enumerator.UserRole;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAll() {
        return repository.findAll();
    }

    public Book getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Book> getByYear(Integer year) {
        return repository.findByYear(year);
    }

    public Book create(User requestor, Book entity) {
        if (!requestor.hasRole(Set.of(UserRole.ADMIN, UserRole.LIBRARIAN))) {
            throw new UnauthorizedActionException();
        }

        return repository.save(entity);
    }

    public Book update(User requestor, String id, Book entity) {
        if (!requestor.hasRole(Set.of(UserRole.ADMIN, UserRole.LIBRARIAN))) {
            throw new UnauthorizedActionException();
        }

        Optional<Book> result = repository.findById(id);

        if(result.isEmpty()) {
            return null;
        }

        Book book = result.get();

        book.setTitle(entity.getTitle());
        book.setYear(entity.getYear());
        book.setAuthors(entity.getAuthors());
        book.setDescription(entity.getDescription());
        book.setCategory(entity.getCategory());
        book.setPublisher(entity.getPublisher());
        book.setIsbn(entity.getIsbn());
        book.setTopics(entity.getTopics());

        return repository.save(book);
    }

    public boolean remove(User requestor, String id) {
        if (!requestor.hasRole(Set.of(UserRole.ADMIN, UserRole.LIBRARIAN))) {
            throw new UnauthorizedActionException();
        }

        Optional<Book> book = repository.findById(id);

        if(!book.isEmpty()) {
            repository.delete(book.get());
            return true;
        }

        return false;
    }
}
