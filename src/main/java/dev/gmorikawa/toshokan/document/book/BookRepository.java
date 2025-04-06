package dev.gmorikawa.toshokan.document.book;

import org.springframework.stereotype.Repository;

import dev.gmorikawa.toshokan.document.DocumentRepository;

@Repository
public interface BookRepository extends DocumentRepository<Book> { }
