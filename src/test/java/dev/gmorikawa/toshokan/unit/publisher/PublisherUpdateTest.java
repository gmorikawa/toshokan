package dev.gmorikawa.toshokan.unit.publisher;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.publisher.Publisher;
import dev.gmorikawa.toshokan.domain.publisher.PublisherService;

@SpringBootTest
public class PublisherUpdateTest {
    @Autowired
    private PublisherService service;

    @Test
    public void testUpdatePublisher() {
        Publisher publisher = new Publisher();

        publisher.setName("Pearson");
        publisher.setDescription("lorem ipsum");

        Publisher savedPublisher = service.create(publisher);

        assertThat(savedPublisher).isNotNull();
        assertThat(savedPublisher.getName()).isEqualTo(publisher.getName());
        assertThat(savedPublisher.getDescription()).isEqualTo(publisher.getDescription());

        savedPublisher.setName("O'Reilly");
        savedPublisher.setDescription("Nothing else matters");

        Publisher updatedPublisher = service.update(savedPublisher.getId(), savedPublisher);

        assertThat(updatedPublisher).isNotNull();
        assertThat(updatedPublisher.getName()).isEqualTo(savedPublisher.getName());
        assertThat(updatedPublisher.getDescription()).isEqualTo(savedPublisher.getDescription());
    }
}
