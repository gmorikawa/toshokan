package dev.gmorikawa.toshokan.unit.author;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.author.Author;
import dev.gmorikawa.toshokan.domain.author.AuthorService;

@SpringBootTest
public class AuthorRemoveTest {
    @Autowired
    private AuthorService service;

    @Test
    public void testRemoveAuthor() {
        Author author = new Author();

        author.setFullname("John Doe");
        author.setBiography("lorem ipsum");

        Author savedAuthor = service.create(author);

        assertThat(savedAuthor).isNotNull();
        assertThat(savedAuthor.getFullname()).isEqualTo(author.getFullname());
        assertThat(savedAuthor.getBiography()).isEqualTo(author.getBiography());

        service.remove(savedAuthor.getId());

        Author searchedAuthor = service.getById(savedAuthor.getId());
        assertThat(searchedAuthor).isNull();
    }
}
