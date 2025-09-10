package dev.gmorikawa.toshokan.domain.document;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository<T extends Document> extends JpaRepository<T, String> {
}
