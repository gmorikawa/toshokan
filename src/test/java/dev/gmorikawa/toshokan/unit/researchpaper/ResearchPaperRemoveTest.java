package dev.gmorikawa.toshokan.unit.researchpaper;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.domain.document.researchpaper.ResearchPaper;
import dev.gmorikawa.toshokan.domain.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.utils.UserFactory;

@SpringBootTest
public class ResearchPaperRemoveTest extends ResearchPaperTestEnvironment {

    @Test
    public void testRemoveResearchPaper() {
        // Mock an admin user that will handle this action
        LoggedUser admin = UserFactory.buildAdmin();

        ResearchPaper paper = new ResearchPaper();
        paper.setTitle("Artificial Intelligence in Healthcare");
        paper.setSummary("Research on AI applications in healthcare diagnostics");
        paper.setKeywords("artificial intelligence, healthcare, machine learning, diagnostics");
        ResearchPaper savedPaper = service.create(admin, paper);

        assertThat(savedPaper).isNotNull();
        assertThat(savedPaper.getTitle()).isEqualTo(paper.getTitle());
        assertThat(savedPaper.getSummary()).isEqualTo(paper.getSummary());
        assertThat(savedPaper.getKeywords()).isEqualTo(paper.getKeywords());

        boolean removed = service.remove(admin, savedPaper.getId());
        assertThat(removed).isTrue();

        ResearchPaper searchedPaper = service.getById(savedPaper.getId());
        assertThat(searchedPaper).isNull();
    }
}