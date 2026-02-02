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
public class CategoryRemoveTest extends CategoryTestEnvironment {

    @Test
    public void testRemoveCategory() {
        // Mock a admin user that will handle this action
        LoggedUser admin = UserFactory.buildAdmin();

        Category category = new Category();
        category.setName("Computer Science");
        Category savedCategory = service.create(admin, category);

        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getName()).isEqualTo(category.getName());

        service.remove(admin, savedCategory.getId());

        Category searchedCategory = service.getById(savedCategory.getId());
        assertThat(searchedCategory).isNull();
    }
}
