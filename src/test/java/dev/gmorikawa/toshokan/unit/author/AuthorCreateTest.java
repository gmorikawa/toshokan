package dev.gmorikawa.toshokan.unit.author;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.author.Author;
import dev.gmorikawa.toshokan.domain.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.utils.UserFactory;

@SpringBootTest
public class AuthorCreateTest extends AuthorTestEnvironment {

    @Test
    public void testCreateAuthor() {
        // Mock a admin user that will handle this action
        LoggedUser admin = UserFactory.buildAdmin();

        // Create a new author and persist it in the database
        Author author = new Author();
        author.setFullname("John Doe");
        author.setBiography("lorem ipsum");
        Author savedAuthor = service.create(admin, author);

        assertThat(savedAuthor).isNotNull();
        assertThat(savedAuthor.getFullname()).isEqualTo(author.getFullname());
        assertThat(savedAuthor.getBiography()).isEqualTo(author.getBiography());

        // clean-up
        clean(savedAuthor);
    }
}
