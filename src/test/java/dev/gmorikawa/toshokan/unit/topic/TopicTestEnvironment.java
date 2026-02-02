package dev.gmorikawa.toshokan.unit.topic;

import org.springframework.beans.factory.annotation.Autowired;

import dev.gmorikawa.toshokan.core.topic.Topic;
import dev.gmorikawa.toshokan.core.topic.TopicRepository;
import dev.gmorikawa.toshokan.core.topic.TopicService;

public abstract class TopicTestEnvironment {

    @Autowired
    protected TopicRepository repository;

    @Autowired
    protected TopicService service;

    protected void clean(Topic topic) {
        repository.delete(topic);
    }

}
