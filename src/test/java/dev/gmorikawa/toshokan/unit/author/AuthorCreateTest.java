package dev.gmorikawa.toshokan.unit.author;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.author.Author;
import dev.gmorikawa.toshokan.domain.author.AuthorService;

@SpringBootTest
public class AuthorCreateTest {

    @Autowired
    private AuthorService service;

    @Test
    public void testCreateAuthor() {
        Author author = new Author();

        String fullname = "John Doe";
        String biography = "lorem ipsum";

        author.setFullname(fullname);
        author.setBiography(biography);

        Author savedAuthor = service.create(author);

        assertThat(savedAuthor).isNotNull();
        assertThat(savedAuthor.getFullname()).isEqualTo(author.getFullname());
        assertThat(savedAuthor.getBiography()).isEqualTo(author.getBiography());
    }
}
