package dev.gmorikawa.toshokan.unit.researchpaper;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dev.gmorikawa.toshokan.core.document.researchpaper.ResearchPaper;
import dev.gmorikawa.toshokan.core.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.utils.UserFactory;

@SpringBootTest
public class ResearchPaperUpdateTest extends ResearchPaperTestEnvironment {

    @Test
    public void testUpdateResearchPaper() {
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

        savedPaper.setTitle("Machine Learning in Medical Diagnosis");
        savedPaper.setSummary("Advanced applications of ML in medical imaging and diagnosis");
        savedPaper.setKeywords("machine learning, medical imaging, diagnosis, deep learning, neural networks");
        ResearchPaper updatedPaper = service.update(admin, savedPaper.getId(), savedPaper);

        assertThat(updatedPaper).isNotNull();
        assertThat(updatedPaper.getTitle()).isEqualTo(savedPaper.getTitle());
        assertThat(updatedPaper.getSummary()).isEqualTo(savedPaper.getSummary());
        assertThat(updatedPaper.getKeywords()).isEqualTo(savedPaper.getKeywords());

        // clean-up
        clean(updatedPaper);
    }
}