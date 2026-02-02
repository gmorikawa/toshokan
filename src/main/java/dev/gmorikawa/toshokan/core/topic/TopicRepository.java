package dev.gmorikawa.toshokan.core.topic;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, UUID> {
    @Query(
        value = "SELECT * FROM topics WHERE LOWER(name) ILIKE LOWER(CONCAT('%', ?1, '%'))",
        nativeQuery = true
    )
    public Page<Topic> searchByName(String query, Pageable pageable);

    @Query(
        value = "SELECT * FROM topics WHERE LOWER(name) ILIKE LOWER(CONCAT('%', ?1, '%'))",
        nativeQuery = true
    )
    public List<Topic> searchByName(String query);

    @Query("SELECT t FROM Topic t WHERE t.name = ?1")
    public Optional<Topic> findByName(String name);
}
