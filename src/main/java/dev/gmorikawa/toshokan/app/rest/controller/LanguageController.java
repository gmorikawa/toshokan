package dev.gmorikawa.toshokan.app.rest.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gmorikawa.toshokan.domain.language.Language;
import dev.gmorikawa.toshokan.domain.language.LanguageService;
import dev.gmorikawa.toshokan.domain.user.User;

@RestController("api.language")
@RequestMapping(path = "api/language")
public class LanguageController {

    private final LanguageService service;

    public LanguageController(LanguageService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Language> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Language getById(
        @PathVariable UUID id
    ) {
        return service.getById(id);
    }

    @GetMapping("/name/{name}")
    public Language getByName(
        @PathVariable String name
    ) {
        return service.getByName(name);
    }

    @PostMapping()
    public Language create(
        @RequestAttribute User user,
        @RequestBody Language language
    ) {
        return service.create(user, language);
    }

    @PatchMapping("/{id}")
    public Language update(
        @RequestAttribute User user,
        @PathVariable UUID id,
        @RequestBody Language language
    ) {
        return service.update(user, id, language);
    }

    @DeleteMapping("/{id}")
    public Language remove(
        @RequestAttribute User user,
        @PathVariable UUID id
    ) {
        return service.remove(user, id);
    }
}
