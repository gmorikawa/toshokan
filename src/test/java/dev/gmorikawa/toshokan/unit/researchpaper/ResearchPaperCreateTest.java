package dev.gmorikawa.toshokan.unit.researchpaper;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.core.document.researchpaper.ResearchPaper;
import dev.gmorikawa.toshokan.core.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.utils.UserFactory;

@SpringBootTest
public class ResearchPaperCreateTest extends ResearchPaperTestEnvironment {

    @Test
    public void testCreateResearchPaper() {
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

        // clean-up
        clean(savedPaper);
    }
}