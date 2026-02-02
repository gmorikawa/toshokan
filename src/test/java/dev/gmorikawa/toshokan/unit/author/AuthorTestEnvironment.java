package dev.gmorikawa.toshokan.unit.author;

import org.springframework.beans.factory.annotation.Autowired;

import dev.gmorikawa.toshokan.core.author.Author;
import dev.gmorikawa.toshokan.core.author.AuthorRepository;
import dev.gmorikawa.toshokan.core.author.AuthorService;

public abstract class AuthorTestEnvironment {

    @Autowired
    protected AuthorRepository repository;

    @Autowired
    protected AuthorService service;

    protected void clean(Author author) {
        repository.delete(author);
    }

}
