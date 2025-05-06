package dev.gmorikawa.toshokan.document;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DocumentRepository<T extends Document> extends JpaRepository<T, String> {
    @Query("SELECT d FROM Document d WHERE d.year = ?1")
    List<T> findByYear(Integer year);
}
