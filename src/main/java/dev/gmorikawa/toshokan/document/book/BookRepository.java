package dev.gmorikawa.toshokan.document.book;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.gmorikawa.toshokan.document.DocumentRepository;

@Repository
public interface BookRepository extends DocumentRepository<Book> {
    @Query("SELECT b FROM Book b WHERE b.isbn = ?1")
    public Optional<Book> findByISBN(String isbn);
}
