package dev.gmorikawa.toshokan.unit.language;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.core.language.Language;
import dev.gmorikawa.toshokan.core.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.utils.UserFactory;

@SpringBootTest
public class LanguageRemoveTest extends LanguageTestEnvironment {

    @Test
    public void testRemoveLanguage() {
        // Mock a admin user that will handle this action
        LoggedUser admin = UserFactory.buildAdmin();

        Language language = new Language();
        language.setName("English");
        Language savedLanguage = service.create(admin, language);

        assertThat(savedLanguage).isNotNull();
        assertThat(savedLanguage.getName()).isEqualTo(language.getName());

        service.remove(admin, savedLanguage.getId());

        Language searchedLanguage = service.getById(savedLanguage.getId());
        assertThat(searchedLanguage).isNull();
    }
}
