package dev.gmorikawa.toshokan.domain.document.file;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.gmorikawa.toshokan.domain.document.Document;

@Repository
public interface DocumentFileRepository extends JpaRepository<DocumentFile, UUID> {
    @Query("SELECT df FROM DocumentFile df WHERE df.document = ?1")
    List<DocumentFile> getFilesByDocument(Document document);
}
