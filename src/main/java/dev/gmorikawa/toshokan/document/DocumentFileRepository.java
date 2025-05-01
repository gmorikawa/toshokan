package dev.gmorikawa.toshokan.document;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.gmorikawa.toshokan.file.File;

@Repository
public interface DocumentFileRepository extends JpaRepository<DocumentFile, String> {
    @Query("SELECT f FROM DocumentFile df JOIN df.file f WHERE df.document = ?1")
    List<File> getFilesByDocument(Document document);
}
