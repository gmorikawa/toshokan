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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.gmorikawa.toshokan.domain.language.Language;
import dev.gmorikawa.toshokan.domain.language.LanguageService;
import dev.gmorikawa.toshokan.domain.user.User;
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
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "0") Integer size
    ) {
        if (page == 0 && size == 0) {
            return service.getAll();
        }
        Pagination pagination = new Pagination(page, size);
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
