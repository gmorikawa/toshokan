package dev.gmorikawa.toshokan.unit.publisher;

import org.springframework.beans.factory.annotation.Autowired;

import dev.gmorikawa.toshokan.core.publisher.Publisher;
import dev.gmorikawa.toshokan.core.publisher.PublisherRepository;
import dev.gmorikawa.toshokan.core.publisher.PublisherService;

public abstract class PublisherTestEnvironment {

    @Autowired
    protected PublisherRepository repository;

    @Autowired
    protected PublisherService service;

    protected void clean(Publisher publisher) {
        repository.delete(publisher);
    }

}
