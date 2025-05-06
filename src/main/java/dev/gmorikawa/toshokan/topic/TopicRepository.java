package dev.gmorikawa.toshokan.topic;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, String> {
    @Query("SELECT t FROM Topic t WHERE t.name = ?1")
    public Optional<Topic> findByName(String name);
}
