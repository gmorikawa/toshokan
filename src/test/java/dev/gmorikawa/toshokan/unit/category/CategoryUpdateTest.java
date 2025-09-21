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
public class CategoryUpdateTest {
    @Autowired
    private CategoryService service;

    @Test
    public void testUpdateCategory() {
        Category category = new Category();

        category.setName("Computer Science");

        Category savedCategory = service.create(category);

        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getName()).isEqualTo(category.getName());

        savedCategory.setName("Mathematics");

        Category updatedCategory = service.update(savedCategory.getId(), savedCategory);

        assertThat(updatedCategory).isNotNull();
        assertThat(updatedCategory.getName()).isEqualTo(savedCategory.getName());
    }

    // @Test
    // public void testUpdateDuplicatedNameFail() {
    //     Category category1 = new Category();
    //     category1.setName("Computer Science");
    //     Category savedCategory1 = service.create(category1);

    //     Category category2 = new Category();
    //     category2.setName("Mathematics");
    //     Category savedCategory2 = service.create(category2);

    //     savedCategory2.setName(savedCategory1.getName());

    //     Exception exception = assertThrows(CategoryNameNotAvailableException.class, () -> {
    //         service.update(savedCategory2.getId(), savedCategory2);
    //     });

    //     String expectedMessage = "Category name is already registered";
    //     String actualMessage = exception.getMessage();

    //     assertTrue(actualMessage.contains(expectedMessage));
    // }
}
