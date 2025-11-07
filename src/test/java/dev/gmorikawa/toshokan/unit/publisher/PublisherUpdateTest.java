package dev.gmorikawa.toshokan.unit.publisher;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.publisher.Publisher;
import dev.gmorikawa.toshokan.domain.user.User;
import dev.gmorikawa.toshokan.utils.UserFactory;

@SpringBootTest
public class PublisherUpdateTest extends PublisherTestEnvironment {

    @Test
    public void testUpdatePublisher() {
        // Mock a admin user that will handle this action
        User admin = UserFactory.buildAdmin();

        Publisher publisher = new Publisher();
        publisher.setName("Pearson");
        publisher.setDescription("lorem ipsum");
        Publisher savedPublisher = service.create(admin, publisher);

        assertThat(savedPublisher).isNotNull();
        assertThat(savedPublisher.getName()).isEqualTo(publisher.getName());
        assertThat(savedPublisher.getDescription()).isEqualTo(publisher.getDescription());

        savedPublisher.setName("O'Reilly");
        savedPublisher.setDescription("Nothing else matters");
        Publisher updatedPublisher = service.update(admin, savedPublisher.getId(), savedPublisher);

        assertThat(updatedPublisher).isNotNull();
        assertThat(updatedPublisher.getName()).isEqualTo(savedPublisher.getName());
        assertThat(updatedPublisher.getDescription()).isEqualTo(savedPublisher.getDescription());

        // clean-up
        clean(updatedPublisher);
    }
}
