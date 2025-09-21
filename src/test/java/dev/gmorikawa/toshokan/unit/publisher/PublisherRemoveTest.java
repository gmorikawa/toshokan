package dev.gmorikawa.toshokan.unit.publisher;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.publisher.Publisher;
import dev.gmorikawa.toshokan.domain.publisher.PublisherService;

@SpringBootTest
public class PublisherRemoveTest {
    @Autowired
    private PublisherService service;

    @Test
    public void testRemovePublisher() {
        Publisher publisher = new Publisher();

        publisher.setName("No Starch");
        publisher.setDescription("Nothing else matters");

        Publisher savedPublisher = service.create(publisher);

        assertThat(savedPublisher).isNotNull();
        assertThat(savedPublisher.getName()).isEqualTo(publisher.getName());
        assertThat(savedPublisher.getDescription()).isEqualTo(publisher.getDescription());

        service.remove(savedPublisher.getId());

        Publisher searchedPublisher = service.getById(savedPublisher.getId());
        assertThat(searchedPublisher).isNull();
    }
}
