package dev.gmorikawa.toshokan.unit.publisher;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.publisher.Publisher;
import dev.gmorikawa.toshokan.domain.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.utils.UserFactory;

@SpringBootTest
public class PublisherCreateTest extends PublisherTestEnvironment {

    @Test
    public void testCreatePublisher() {
        // Mock a admin user that will handle this action
        LoggedUser admin = UserFactory.buildAdmin();

        Publisher publisher = new Publisher();

        String fullname = "John Doe";
        String description = "lorem ipsum";

        publisher.setName(fullname);
        publisher.setDescription(description);

        Publisher savedPublisher = service.create(admin, publisher);

        assertThat(savedPublisher).isNotNull();
        assertThat(savedPublisher.getName()).isEqualTo(publisher.getName());
        assertThat(savedPublisher.getDescription()).isEqualTo(publisher.getDescription());

        // clean-up
        clean(savedPublisher);
    }
}
