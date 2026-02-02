package dev.gmorikawa.toshokan.unit.author;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.core.author.Author;
import dev.gmorikawa.toshokan.core.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.utils.UserFactory;

@SpringBootTest
public class AuthorUpdateTest extends AuthorTestEnvironment {

    @Test
    public void testUpdateAuthor() {
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

        // Modify the created author apply the modifications
        savedAuthor.setFullname("Joe Don");
        savedAuthor.setBiography("Nothing else matters");
        Author updatedAuthor = service.update(admin, savedAuthor.getId(), savedAuthor);

        assertThat(updatedAuthor).isNotNull();
        assertThat(updatedAuthor.getFullname()).isEqualTo(savedAuthor.getFullname());
        assertThat(updatedAuthor.getBiography()).isEqualTo(savedAuthor.getBiography());

        // clean-up
        clean(updatedAuthor);
    }
}
