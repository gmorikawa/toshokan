package dev.gmorikawa.toshokan.domain.topic;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, UUID> {
    @Query("SELECT t FROM Topic t WHERE t.name = ?1")
    public Optional<Topic> findByName(String name);
}
