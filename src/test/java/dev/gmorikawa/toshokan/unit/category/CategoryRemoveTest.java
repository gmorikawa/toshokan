package dev.gmorikawa.toshokan.unit.category;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import dev.gmorikawa.toshokan.domain.category.Category;
import dev.gmorikawa.toshokan.domain.category.CategoryService;

@SpringBootTest
@Transactional
public class CategoryRemoveTest {
    @Autowired
    private CategoryService service;

    @Test
    public void testRemoveCategory() {
        Category category = new Category();

        category.setName("Computer Science");

        Category savedCategory = service.create(category);

        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getName()).isEqualTo(category.getName());

        service.remove(savedCategory.getId());

        Category searchedCategory = service.getById(savedCategory.getId());
        assertThat(searchedCategory).isNull();
    }
}
