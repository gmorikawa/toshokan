package dev.gmorikawa.toshokan.domain.document.whitepaper;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.gmorikawa.toshokan.domain.document.DocumentRepository;

@Repository
public interface WhitepaperRepository extends DocumentRepository<Whitepaper> {
    @Query("SELECT wp FROM Whitepaper wp WHERE LOWER(wp.title) LIKE LOWER(CONCAT('%', :string, '%'))")
    public List<Whitepaper> searchByTitle(String string);

    @Query("SELECT wp FROM Whitepaper wp WHERE LOWER(wp.title) LIKE LOWER(CONCAT('%', :string, '%'))")
    public Page<Whitepaper> searchByTitle(String string, Pageable pageable);
}
