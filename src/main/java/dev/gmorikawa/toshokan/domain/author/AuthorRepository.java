package dev.gmorikawa.toshokan.domain.author;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {
    @Query("SELECT a FROM Author a WHERE a.fullname = ?1")
    public Optional<Author> findByFullname(String fullname);
}
