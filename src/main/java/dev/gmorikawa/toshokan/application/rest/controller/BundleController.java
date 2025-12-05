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

import dev.gmorikawa.toshokan.domain.bundle.Bundle;
import dev.gmorikawa.toshokan.domain.bundle.BundleService;
import dev.gmorikawa.toshokan.domain.user.User;
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
    public Bundle getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping()
    public Bundle create(
        @RequestAttribute User user,
        @RequestBody Bundle bundle
    ) {
        return service.create(user, bundle);
    }

    @PatchMapping("/{id}")
    public Bundle update(
        @RequestAttribute User user,
        @PathVariable UUID id,
        @RequestBody Bundle bundle
    ) {
        return service.update(user, id, bundle);
    }

    @DeleteMapping("/{id}")
    public void remove(
        @RequestAttribute User user,
        @PathVariable UUID id
    ) {
        service.remove(user, id);
    }
}
