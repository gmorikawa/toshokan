package dev.gmorikawa.toshokan.domain.document;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository<T extends Document> extends JpaRepository<T, UUID> { }
