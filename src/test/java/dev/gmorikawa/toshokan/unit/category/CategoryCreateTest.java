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
public class CategoryCreateTest {

    @Autowired
    private CategoryService service;

    @Test
    public void testCreateCategory() {
        Category category = new Category();

        category.setName("Computer Science");

        Category savedCategory = service.create(category);

        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getName()).isEqualTo(category.getName());
    }

    // @Test
    // public void testCreateDuplicatedNameFail() {
    //     String name = "Computer Science";

    //     Category category = new Category();
    //     category.setName(name);
    //     service.create(category);

    //     Exception exception = assertThrows(CategoryNameNotAvailableException.class, () -> {
    //         Category category2 = new Category();
    //         category2.setName(name);
    //         service.create(category2);
    //     });

    //     String expectedMessage = "Category name is already registered";
    //     String actualMessage = exception.getMessage();

    //     assertTrue(actualMessage.contains(expectedMessage));
    // }
}
