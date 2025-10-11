package dev.gmorikawa.toshokan.domain.document.book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.gmorikawa.toshokan.shared.query.Pagination;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAll(Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.page - 1, pagination.size);
        Page<Book> page = repository.findAll(pageable);

        return page.getContent();
    }

    public List<Book> getAll() {
        return repository.findAll();
    }

    public Book getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public Book create(Book entity) {
        return repository.save(entity);
    }

    public Book update(UUID id, Book entity) {
        Optional<Book> result = repository.findById(id);

        if (result.isEmpty()) {
            return null;
        }

        Book book = result.get();

        book.setTitle(entity.getTitle());
        book.setAuthors(entity.getAuthors());
        book.setDescription(entity.getDescription());
        book.setCategory(entity.getCategory());
        book.setPublisher(entity.getPublisher());
        book.setIsbn(entity.getIsbn());
        book.setTopics(entity.getTopics());
        book.setType(entity.getType());

        return repository.save(book);
    }

    public boolean remove(UUID id) {
        Optional<Book> book = repository.findById(id);

        if (!book.isEmpty()) {
            repository.delete(book.get());
            return true;
        }

        return false;
    }
}
