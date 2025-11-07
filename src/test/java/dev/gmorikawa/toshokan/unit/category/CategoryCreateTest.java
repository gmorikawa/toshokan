package dev.gmorikawa.toshokan.unit.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import dev.gmorikawa.toshokan.domain.category.Category;
import dev.gmorikawa.toshokan.domain.category.exception.CategoryNameNotAvailableException;
import dev.gmorikawa.toshokan.domain.user.User;
import dev.gmorikawa.toshokan.utils.UserFactory;

@SpringBootTest
@Transactional
public class CategoryCreateTest extends CategoryTestEnvironment {

    @Test
    public void testCreateCategory() {
        // Mock a admin user that will handle this action
        User admin = UserFactory.buildAdmin();

        // Create a new category and persist it in the database
        Category category = new Category();
        category.setName("Computer Science");
        Category savedCategory = service.create(admin, category);

        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getName()).isEqualTo(category.getName());

        // clean-up
        clean(savedCategory);
    }

    @Test
    public void testCreateDuplicatedNameFail() {
        // Mock a admin user that will handle this action
        User admin = UserFactory.buildAdmin();

        String name = "Computer Science";
        Category category = new Category();
        category.setName(name);
        service.create(admin, category);

        Exception exception = assertThrows(CategoryNameNotAvailableException.class, () -> {
            Category category2 = new Category();
            category2.setName(name);
            service.create(admin, category2);
        });

        String expectedMessage = "Category name is already registered";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
