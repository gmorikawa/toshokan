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

import dev.gmorikawa.toshokan.domain.organization.Organization;
import dev.gmorikawa.toshokan.domain.organization.OrganizationService;
import dev.gmorikawa.toshokan.domain.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.shared.query.Pagination;

@RestController("api.organization")
@RequestMapping("api/organizations")
public class OrganizationController {
    
    private final OrganizationService service;

    public OrganizationController(OrganizationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Organization> getAll(
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
    public Organization getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public Organization create(
        @RequestAttribute LoggedUser loggedUser,
        @RequestBody Organization organization
    ) {
        return service.create(loggedUser, organization);
    }

    @PatchMapping("/{id}")
    public Organization update(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id,
        @RequestBody Organization organization
    ) {
        return service.update(loggedUser, id, organization);
    }

    @DeleteMapping("/{id}")
    public Organization remove(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id
    ) {
        return service.remove(loggedUser, id);
    }
}
