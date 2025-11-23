package dev.gmorikawa.toshokan.unit.book;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.document.book.Book;
import dev.gmorikawa.toshokan.domain.document.book.enumerator.BookType;
import dev.gmorikawa.toshokan.domain.user.User;
import dev.gmorikawa.toshokan.utils.UserFactory;

@SpringBootTest
public class BookUpdateTest extends BookTestEnvironment {

    @Test
    public void testUpdateBook() {
        // Mock an admin user that will handle this action
        User admin = UserFactory.buildAdmin();

        Book book = new Book();
        book.setTitle("Clean Code");
        book.setSummary("A Handbook of Agile Software Craftsmanship");
        book.setType(BookType.NON_FICTION);
        Book savedBook = service.create(admin, book);

        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo(book.getTitle());
        assertThat(savedBook.getSummary()).isEqualTo(book.getSummary());
        assertThat(savedBook.getType()).isEqualTo(book.getType());

        savedBook.setTitle("Clean Architecture");
        savedBook.setSummary("A Craftsman's Guide to Software Structure and Design");
        savedBook.setType(BookType.NON_FICTION);
        Book updatedBook = service.update(admin, savedBook.getId(), savedBook);

        assertThat(updatedBook).isNotNull();
        assertThat(updatedBook.getTitle()).isEqualTo(savedBook.getTitle());
        assertThat(updatedBook.getSummary()).isEqualTo(savedBook.getSummary());
        assertThat(updatedBook.getType()).isEqualTo(savedBook.getType());

        // clean-up
        clean(updatedBook);
    }
}