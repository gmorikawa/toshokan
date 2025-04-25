package dev.gmorikawa.toshokan.document.book;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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

    public Book getByYear(Integer year) {
        return repository.findByYear(year).orElse(null);
    }

    public Book insert(Book entity) {
        return repository.save(entity);
    }

    public Book update(String id, Book entity) {
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

    public boolean remove(String id) {
        Optional<Book> book = repository.findById(id);

        if(!book.isEmpty()) {
            repository.delete(book.get());
            return true;
        }

        return false;
    }
}
