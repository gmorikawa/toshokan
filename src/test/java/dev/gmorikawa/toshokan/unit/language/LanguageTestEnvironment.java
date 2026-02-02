package dev.gmorikawa.toshokan.unit.language;

import org.springframework.beans.factory.annotation.Autowired;

import dev.gmorikawa.toshokan.core.language.Language;
import dev.gmorikawa.toshokan.core.language.LanguageRepository;
import dev.gmorikawa.toshokan.core.language.LanguageService;

public abstract class LanguageTestEnvironment {

    @Autowired
    protected LanguageRepository repository;

    @Autowired
    protected LanguageService service;

    protected void clean(Language language) {
        repository.delete(language);
    }

}
