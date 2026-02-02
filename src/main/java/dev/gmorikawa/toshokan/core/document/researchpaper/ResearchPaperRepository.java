package dev.gmorikawa.toshokan.core.document.researchpaper;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.gmorikawa.toshokan.core.document.DocumentRepository;

@Repository
public interface ResearchPaperRepository extends DocumentRepository<ResearchPaper> {
    @Query("SELECT rp FROM ResearchPaper rp WHERE LOWER(rp.title) LIKE LOWER(CONCAT('%', :string, '%'))")
    public List<ResearchPaper> searchByTitle(String string);

    @Query("SELECT rp FROM ResearchPaper rp WHERE LOWER(rp.title) LIKE LOWER(CONCAT('%', :string, '%'))")
    public Page<ResearchPaper> searchByTitle(String string, Pageable pageable);
}
