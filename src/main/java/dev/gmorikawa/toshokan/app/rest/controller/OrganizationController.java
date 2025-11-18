package dev.gmorikawa.toshokan.app.rest.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gmorikawa.toshokan.domain.organization.Organization;
import dev.gmorikawa.toshokan.domain.organization.OrganizationService;
import dev.gmorikawa.toshokan.domain.user.User;

@RestController
@RequestMapping("api/organizations")
public class OrganizationController {
    
    private final OrganizationService service;

    public OrganizationController(OrganizationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Organization> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Organization getById(@PathVariable UUID id) {
        Organization organization = service.getById(id);

        return organization;
    }

    @PostMapping
    public Organization create(
        @AuthenticationPrincipal User user,
        @RequestBody Organization organization
    ) {
        Organization result = service.create(user, organization);

        return result;
    }

    @PatchMapping("/{id}")
    public Organization update(
        @AuthenticationPrincipal User user,
        @PathVariable UUID id,
        @RequestBody Organization organization
    ) {
        Organization result = service.update(user, id, organization);

        return result;
    }

    @DeleteMapping("/{id}")
    public Organization remove(
        @AuthenticationPrincipal User user,
        @PathVariable UUID id
    ) {
        Organization organization = service.remove(user, id);

        return organization;
    }
}
