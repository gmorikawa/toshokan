package dev.gmorikawa.toshokan.core.bundle;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BundleRepository extends JpaRepository<Bundle, UUID> {
    @Query("SELECT b FROM Bundle b WHERE b.title = ?1")
    public Optional<Bundle> findByTitle(String title);
}
