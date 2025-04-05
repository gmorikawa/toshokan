package dev.gmorikawa.toshokan.category;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryConfig {
    
    @Bean
    CommandLineRunner registerCategories(CategoryRepository repository) {
        return args -> {
            Category c1 = new Category("Science & Technology");
            Category c2 = new Category("Humanities & Social Sciences");
            Category c3 = new Category("Art & Photography");

            repository.saveAll(List.of(c1, c2, c3));
        };
    }
}
