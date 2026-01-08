package dev.gmorikawa.toshokan.unit.language;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.language.Language;
import dev.gmorikawa.toshokan.domain.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.utils.UserFactory;

@SpringBootTest
public class LanguageCreateTest extends LanguageTestEnvironment {

    @Test
    public void testCreateLanguage() {
        // Mock a admin user that will handle this action
        LoggedUser admin = UserFactory.buildAdmin();

        Language language = new Language();
        language.setName("English");
        Language savedLanguage = service.create(admin, language);

        assertThat(savedLanguage).isNotNull();
        assertThat(savedLanguage.getName()).isEqualTo(language.getName());

        // clean-up
        clean(savedLanguage);
    }
}
