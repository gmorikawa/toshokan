package dev.gmorikawa.toshokan.unit.book;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.core.document.book.Book;
import dev.gmorikawa.toshokan.core.document.book.enumerator.BookType;
import dev.gmorikawa.toshokan.core.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.utils.UserFactory;

@SpringBootTest
public class BookRemoveTest extends BookTestEnvironment {

    @Test
    public void testRemoveBook() {
        // Mock an admin user that will handle this action
        LoggedUser admin = UserFactory.buildAdmin();

        Book book = new Book();
        book.setTitle("Clean Code");
        book.setSummary("A Handbook of Agile Software Craftsmanship");
        book.setType(BookType.NON_FICTION);
        Book savedBook = service.create(admin, book);

        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo(book.getTitle());
        assertThat(savedBook.getSummary()).isEqualTo(book.getSummary());
        assertThat(savedBook.getType()).isEqualTo(book.getType());

        boolean removed = service.remove(admin, savedBook.getId());
        assertThat(removed).isTrue();

        Book searchedBook = service.getById(savedBook.getId());
        assertThat(searchedBook).isNull();
    }
}