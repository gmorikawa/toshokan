package dev.gmorikawa.toshokan.unit.book;

import org.springframework.beans.factory.annotation.Autowired;

import dev.gmorikawa.toshokan.domain.document.book.Book;
import dev.gmorikawa.toshokan.domain.document.book.BookRepository;
import dev.gmorikawa.toshokan.domain.document.book.BookService;

public abstract class BookTestEnvironment {

    @Autowired
    protected BookRepository repository;

    @Autowired
    protected BookService service;

    protected void clean(Book book) {
        repository.delete(book);
    }
}