package dev.gmorikawa.toshokan.unit.category;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import dev.gmorikawa.toshokan.core.category.Category;
import dev.gmorikawa.toshokan.core.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.utils.UserFactory;

@SpringBootTest
@Transactional
public class CategoryUpdateTest extends CategoryTestEnvironment{

    @Test
    public void testUpdateCategory() {
        // Mock a admin user that will handle this action
        LoggedUser admin = UserFactory.buildAdmin();

        Category category = new Category();
        category.setName("Computer Science");
        Category savedCategory = service.create(admin, category);

        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getName()).isEqualTo(category.getName());

        savedCategory.setName("Mathematics");
        Category updatedCategory = service.update(admin, savedCategory.getId(), savedCategory);

        assertThat(updatedCategory).isNotNull();
        assertThat(updatedCategory.getName()).isEqualTo(savedCategory.getName());

        // clean-up
        clean(updatedCategory);
    }

    // @Test
    // public void testUpdateDuplicatedNameFail() {
    //     // Mock a admin user that will handle this action
    //     LoggedUser admin = UserFactory.buildAdmin();

    //     Category category1 = new Category();
    //     category1.setName("Economics");
    //     Category savedCategory1 = service.create(admin, category1);

    //     Category category2 = new Category();
    //     category2.setName("Psychology");
    //     Category savedCategory2 = service.create(admin, category2);

    //     Exception exception = assertThrows(CategoryNameNotAvailableException.class, () -> {
    //         savedCategory2.setName(savedCategory1.getName());
    //         service.update(admin, savedCategory2.getId(), savedCategory2);
    //     });

    //     String expectedMessage = "Category name is already registered";
    //     String actualMessage = exception.getMessage();

    //     assertTrue(actualMessage.contains(expectedMessage));

    //     // clean-up
    //     clean(savedCategory1);
    //     clean(savedCategory2);
    // }
}
