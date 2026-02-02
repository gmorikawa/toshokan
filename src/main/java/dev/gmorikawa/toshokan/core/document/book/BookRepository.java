package dev.gmorikawa.toshokan.core.document.book;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.gmorikawa.toshokan.core.document.DocumentRepository;

@Repository
public interface BookRepository extends DocumentRepository<Book> {
    @Query("SELECT b FROM Book b WHERE LOWER(CONCAT(b.title, ' ', b.subtitle)) LIKE LOWER(CONCAT('%', :string, '%'))")
    public List<Book> searchByTitle(String string);

    @Query("SELECT b FROM Book b WHERE LOWER(CONCAT(b.title, ' ', b.subtitle)) LIKE LOWER(CONCAT('%', :string, '%'))")
    public Page<Book> searchByTitle(String string, Pageable pageable);
}
