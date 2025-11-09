package dev.gmorikawa.toshokan.unit.researchpaper;

import org.springframework.beans.factory.annotation.Autowired;

import dev.gmorikawa.toshokan.domain.document.researchpaper.ResearchPaper;
import dev.gmorikawa.toshokan.domain.document.researchpaper.ResearchPaperRepository;
import dev.gmorikawa.toshokan.domain.document.researchpaper.ResearchPaperService;

public abstract class ResearchPaperTestEnvironment {

    @Autowired
    protected ResearchPaperRepository repository;

    @Autowired
    protected ResearchPaperService service;

    protected void clean(ResearchPaper researchPaper) {
        repository.delete(researchPaper);
    }
}