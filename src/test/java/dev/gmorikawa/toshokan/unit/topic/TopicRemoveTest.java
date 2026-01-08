package dev.gmorikawa.toshokan.unit.topic;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.topic.Topic;
import dev.gmorikawa.toshokan.domain.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.utils.UserFactory;

@SpringBootTest
public class TopicRemoveTest extends TopicTestEnvironment {

    @Test
    public void testRemoveTopic() {
        // Mock a admin user that will handle this action
        LoggedUser admin = UserFactory.buildAdmin();

        Topic topic = new Topic();
        topic.setName("Database");
        Topic savedTopic = service.create(admin, topic);

        assertThat(savedTopic).isNotNull();
        assertThat(savedTopic.getName()).isEqualTo(topic.getName());

        service.remove(admin, savedTopic.getId());

        Topic searchedTopic = service.getById(savedTopic.getId());
        assertThat(searchedTopic).isNull();
    }
}
