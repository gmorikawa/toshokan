package dev.gmorikawa.toshokan.application.rest.controller;

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
import dev.gmorikawa.toshokan.domain.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.shared.query.Pagination;

@RestController("api.language")
@RequestMapping(path = "api/languages")
public class LanguageController {

    private final LanguageService service;

    public LanguageController(LanguageService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Language> getAll(
        @RequestAttribute(required = false) Pagination pagination
    ) {
        if (pagination == null) {
            return service.getAll();
        }

        return service.getAll(pagination);
    }

    @GetMapping("/count")
    public Integer countAll() {
        return service.countAll();
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
        @RequestAttribute LoggedUser loggedUser,
        @RequestBody Language language
    ) {
        return service.create(loggedUser, language);
    }

    @PatchMapping("/{id}")
    public Language update(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id,
        @RequestBody Language language
    ) {
        return service.update(loggedUser, id, language);
    }

    @DeleteMapping("/{id}")
    public Language remove(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id
    ) {
        return service.remove(loggedUser, id);
    }
}
