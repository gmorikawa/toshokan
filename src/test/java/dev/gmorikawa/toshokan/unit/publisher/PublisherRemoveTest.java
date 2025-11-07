package dev.gmorikawa.toshokan.unit.publisher;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.publisher.Publisher;
import dev.gmorikawa.toshokan.domain.user.User;
import dev.gmorikawa.toshokan.utils.UserFactory;

@SpringBootTest
public class PublisherRemoveTest extends PublisherTestEnvironment {

    @Test
    public void testRemovePublisher() {
        // Mock a admin user that will handle this action
        User admin = UserFactory.buildAdmin();

        Publisher publisher = new Publisher();
        publisher.setName("No Starch");
        publisher.setDescription("Nothing else matters");
        Publisher savedPublisher = service.create(admin, publisher);

        assertThat(savedPublisher).isNotNull();
        assertThat(savedPublisher.getName()).isEqualTo(publisher.getName());
        assertThat(savedPublisher.getDescription()).isEqualTo(publisher.getDescription());

        service.remove(admin, savedPublisher.getId());

        Publisher searchedPublisher = service.getById(savedPublisher.getId());
        assertThat(searchedPublisher).isNull();
    }
}
