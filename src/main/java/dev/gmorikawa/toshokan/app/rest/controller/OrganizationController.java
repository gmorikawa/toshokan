package dev.gmorikawa.toshokan.app.rest.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gmorikawa.toshokan.domain.organization.Organization;
import dev.gmorikawa.toshokan.domain.organization.OrganizationService;
import dev.gmorikawa.toshokan.domain.user.User;
import dev.gmorikawa.toshokan.shared.query.Pagination;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {
    
    private final OrganizationService service;

    public OrganizationController(OrganizationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Organization>> getAll(Pagination pagination) {
        return ResponseEntity.ok(service.getAll(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organization> getById(@PathVariable UUID id) {
        Organization organization = service.getById(id);

        if(organization == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(organization);
    }

    @PostMapping
    public ResponseEntity<Organization> create(
        @AuthenticationPrincipal User user,
        @RequestBody Organization organization
    ) {
        Organization result = service.create(user, organization);

        if(result == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organization> update(
        @AuthenticationPrincipal User user,
        @PathVariable UUID id,
        @RequestBody Organization organization
    ) {
        Organization result = service.update(user, id, organization);

        if(result == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Organization> remove(
        @AuthenticationPrincipal User user,
        @PathVariable UUID id
    ) {
        Organization organization = service.remove(user, id);

        if(organization == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(organization);
    }
}
