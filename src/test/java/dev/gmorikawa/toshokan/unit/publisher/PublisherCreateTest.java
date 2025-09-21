package dev.gmorikawa.toshokan.unit.publisher;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.publisher.Publisher;
import dev.gmorikawa.toshokan.domain.publisher.PublisherService;

@SpringBootTest
public class PublisherCreateTest {

    @Autowired
    private PublisherService service;

    @Test
    public void testCreatePublisher() {
        Publisher publisher = new Publisher();

        String fullname = "John Doe";
        String description = "lorem ipsum";

        publisher.setName(fullname);
        publisher.setDescription(description);

        Publisher savedPublisher = service.create(publisher);

        assertThat(savedPublisher).isNotNull();
        assertThat(savedPublisher.getName()).isEqualTo(publisher.getName());
        assertThat(savedPublisher.getDescription()).isEqualTo(publisher.getDescription());
    }
}
