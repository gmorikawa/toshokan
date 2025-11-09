package dev.gmorikawa.toshokan.unit.whitepaper;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.document.whitepaper.Whitepaper;
import dev.gmorikawa.toshokan.domain.user.User;
import dev.gmorikawa.toshokan.utils.UserFactory;

@SpringBootTest
public class WhitepaperUpdateTest extends WhitepaperTestEnvironment {

    @Test
    public void testUpdateWhitepaper() {
        // Mock an admin user that will handle this action
        User admin = UserFactory.buildAdmin();

        Whitepaper whitepaper = new Whitepaper();
        whitepaper.setTitle("Zero Trust Architecture");
        whitepaper.setSummary("A comprehensive guide to implementing Zero Trust security model");
        Whitepaper savedWhitepaper = service.create(admin, whitepaper);

        assertThat(savedWhitepaper).isNotNull();
        assertThat(savedWhitepaper.getTitle()).isEqualTo(whitepaper.getTitle());
        assertThat(savedWhitepaper.getSummary()).isEqualTo(whitepaper.getSummary());

        savedWhitepaper.setTitle("Zero Trust Architecture Design Principles");
        savedWhitepaper.setSummary("Best practices and design principles for Zero Trust implementation");
        Whitepaper updatedWhitepaper = service.update(admin, savedWhitepaper.getId(), savedWhitepaper);

        assertThat(updatedWhitepaper).isNotNull();
        assertThat(updatedWhitepaper.getTitle()).isEqualTo(savedWhitepaper.getTitle());
        assertThat(updatedWhitepaper.getSummary()).isEqualTo(savedWhitepaper.getSummary());

        // clean-up
        clean(updatedWhitepaper);
    }
}