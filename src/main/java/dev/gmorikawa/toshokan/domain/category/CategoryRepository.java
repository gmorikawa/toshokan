package dev.gmorikawa.toshokan.domain.category;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query("SELECT c FROM Category c WHERE c.name = ?1")
    public Optional<Category> findByName(String name);

    @Query("SELECT c FROM Category c WHERE c.name = ?1 AND c.id <> ?2")
    public Optional<Category> findByName(String name, UUID ignoreId);
}
