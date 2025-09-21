package dev.gmorikawa.toshokan.unit.topic;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.topic.Topic;
import dev.gmorikawa.toshokan.domain.topic.TopicService;

@SpringBootTest
public class TopicRemoveTest {
    @Autowired
    private TopicService service;

    @Test
    public void testRemoveTopic() {
        Topic topic = new Topic();

        topic.setName("Database");

        Topic savedTopic = service.create(topic);

        assertThat(savedTopic).isNotNull();
        assertThat(savedTopic.getName()).isEqualTo(topic.getName());

        service.remove(savedTopic.getId());

        Topic searchedTopic = service.getById(savedTopic.getId());
        assertThat(searchedTopic).isNull();
    }
}
