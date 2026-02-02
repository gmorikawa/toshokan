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

import dev.gmorikawa.toshokan.domain.bundle.Bundle;
import dev.gmorikawa.toshokan.domain.bundle.BundleService;
import dev.gmorikawa.toshokan.domain.user.entity.LoggedUser;
import dev.gmorikawa.toshokan.shared.query.Pagination;

@RestController("api.bundle")
@RequestMapping(path = "api/bundles")
public class BundleController {
    
    private final BundleService service;
    
    public BundleController(
        BundleService service
    ) {
        this.service = service;
    }
    
    @GetMapping()
    public List<Bundle> getAll(
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
    public Bundle getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping()
    public Bundle create(
        @RequestAttribute LoggedUser loggedUser,
        @RequestBody Bundle bundle
    ) {
        return service.create(loggedUser, bundle);
    }

    @PatchMapping("/{id}")
    public Bundle update(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id,
        @RequestBody Bundle bundle
    ) {
        return service.update(loggedUser, id, bundle);
    }

    @DeleteMapping("/{id}")
    public void remove(
        @RequestAttribute LoggedUser loggedUser,
        @PathVariable UUID id
    ) {
        service.remove(loggedUser, id);
    }
}
