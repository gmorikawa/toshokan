package dev.gmorikawa.toshokan.unit.topic;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.core.topic.Topic;
import dev.gmorikawa.toshokan.core.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.utils.UserFactory;

@SpringBootTest
public class TopicUpdateTest extends TopicTestEnvironment {

    @Test
    public void testUpdateTopic() {
        // Mock a admin user that will handle this action
        LoggedUser admin = UserFactory.buildAdmin();

        Topic topic = new Topic();
        topic.setName("Crytography");
        Topic savedTopic = service.create(admin, topic);

        assertThat(savedTopic).isNotNull();
        assertThat(savedTopic.getName()).isEqualTo(topic.getName());

        savedTopic.setName("Algorithms");
        Topic updatedTopic = service.update(admin, savedTopic.getId(), savedTopic);

        assertThat(updatedTopic).isNotNull();
        assertThat(updatedTopic.getName()).isEqualTo(savedTopic.getName());

        // clean-up
        clean(updatedTopic);
    }
}
