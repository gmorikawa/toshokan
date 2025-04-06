package dev.gmorikawa.toshokan.topic;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {
    
    @Bean
    CommandLineRunner registerTopics(TopicRepository repository) {
        return args -> {
            repository.saveAll(List.of(
                new Topic("Algorithms & Data Structures"),
                new Topic("Networking"),
                new Topic("Software Engineering")
            ));
        };
    }
}
