package dev.gmorikawa.toshokan.publisher;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, String> {
    @Query("SELECT p FROM Publisher p WHERE p.name = ?1")
    public Optional<Publisher> findByName(String name);
}
