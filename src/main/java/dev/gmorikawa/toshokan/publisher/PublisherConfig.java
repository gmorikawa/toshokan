package dev.gmorikawa.toshokan.publisher;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherConfig {
    
    @Bean
    CommandLineRunner registerPublishers(PublisherRepository repository) {
        return args -> {
            Publisher p1 = new Publisher("NoStarch");
            Publisher p2 = new Publisher("O'Reilly");
            Publisher p3 = new Publisher("Pragmatic Programmers");

            repository.saveAll(List.of(p1, p2, p3));
        };
    }
}
