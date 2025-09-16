package dev.gmorikawa.toshokan.unit.author;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.author.Author;
import dev.gmorikawa.toshokan.domain.author.AuthorService;

@SpringBootTest
public class AuthorUpdateTest {
    @Autowired
    private AuthorService service;

    @Test
    public void testUpdateAuthor() {
        Author author = new Author();

        author.setFullname("John Doe");
        author.setBiography("lorem ipsum");

        Author savedAuthor = service.create(author);

        assertThat(savedAuthor).isNotNull();
        assertThat(savedAuthor.getFullname()).isEqualTo(author.getFullname());
        assertThat(savedAuthor.getBiography()).isEqualTo(author.getBiography());

        savedAuthor.setFullname("Joe Don");
        savedAuthor.setBiography("Nothing else matters");

        Author updatedAuthor = service.update(savedAuthor.getId(), savedAuthor);

        assertThat(updatedAuthor).isNotNull();
        assertThat(updatedAuthor.getFullname()).isEqualTo(savedAuthor.getFullname());
        assertThat(updatedAuthor.getBiography()).isEqualTo(savedAuthor.getBiography());
    }
}
