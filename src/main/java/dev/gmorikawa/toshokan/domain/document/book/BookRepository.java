package dev.gmorikawa.toshokan.domain.document.book;

import org.springframework.stereotype.Repository;

import dev.gmorikawa.toshokan.domain.document.DocumentRepository;

@Repository
public interface BookRepository extends DocumentRepository<Book> { }
