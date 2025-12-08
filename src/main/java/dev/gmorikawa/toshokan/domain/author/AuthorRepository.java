package dev.gmorikawa.toshokan.domain.author;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {
    @Query(
        value = "SELECT * FROM authors WHERE LOWER(fullname) ILIKE LOWER(CONCAT('%', ?1, '%'))", 
        nativeQuery = true
    )
    Page<Author> searchByFullname(String query, Pageable pageable);

    @Query(
        value = "SELECT * FROM authors WHERE LOWER(fullname) ILIKE LOWER(CONCAT('%', ?1, '%'))", 
        nativeQuery = true
    )
    public List<Author> searchByFullname(String query);

    @Query("SELECT a FROM Author a WHERE a.fullname = ?1")
    public Optional<Author> findByFullname(String fullname);
}
