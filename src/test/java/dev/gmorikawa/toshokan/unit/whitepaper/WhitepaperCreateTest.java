package dev.gmorikawa.toshokan.unit.whitepaper;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.core.document.whitepaper.Whitepaper;
import dev.gmorikawa.toshokan.core.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.utils.UserFactory;

@SpringBootTest
public class WhitepaperCreateTest extends WhitepaperTestEnvironment {

    @Test
    public void testCreateWhitepaper() {
        // Mock an admin user that will handle this action
        LoggedUser admin = UserFactory.buildAdmin();

        Whitepaper whitepaper = new Whitepaper();
        whitepaper.setTitle("Zero Trust Architecture");
        whitepaper.setSummary("A comprehensive guide to implementing Zero Trust security model");

        Whitepaper savedWhitepaper = service.create(admin, whitepaper);

        assertThat(savedWhitepaper).isNotNull();
        assertThat(savedWhitepaper.getTitle()).isEqualTo(whitepaper.getTitle());
        assertThat(savedWhitepaper.getSummary()).isEqualTo(whitepaper.getSummary());

        // clean-up
        clean(savedWhitepaper);
    }
}