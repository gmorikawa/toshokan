package dev.gmorikawa.toshokan.unit.topic;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.topic.Topic;
import dev.gmorikawa.toshokan.domain.topic.TopicService;

@SpringBootTest
public class TopicUpdateTest {
    @Autowired
    private TopicService service;

    @Test
    public void testUpdateTopic() {
        Topic topic = new Topic();

        topic.setName("Crytography");

        Topic savedTopic = service.create(topic);

        assertThat(savedTopic).isNotNull();
        assertThat(savedTopic.getName()).isEqualTo(topic.getName());

        savedTopic.setName("Algorithms");

        Topic updatedTopic = service.update(savedTopic.getId(), savedTopic);

        assertThat(updatedTopic).isNotNull();
        assertThat(updatedTopic.getName()).isEqualTo(savedTopic.getName());
    }
}
