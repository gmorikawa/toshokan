package dev.gmorikawa.toshokan.core.language;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, UUID> {
    @Query("SELECT t FROM Language t WHERE t.name = ?1")
    public Optional<Language> findByName(String name);
}
