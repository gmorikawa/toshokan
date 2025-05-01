package dev.gmorikawa.toshokan.document;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentFileRepository extends JpaRepository<DocumentFile, String> { }
