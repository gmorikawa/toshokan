package dev.gmorikawa.toshokan.author;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {
    @Query("SELECT a FROM Author a WHERE a.name = ?1")
    public Optional<Author> findByName(String name);
}
