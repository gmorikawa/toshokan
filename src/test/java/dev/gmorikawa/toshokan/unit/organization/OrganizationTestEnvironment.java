package dev.gmorikawa.toshokan.unit.organization;

import org.springframework.beans.factory.annotation.Autowired;

import dev.gmorikawa.toshokan.core.organization.Organization;
import dev.gmorikawa.toshokan.core.organization.OrganizationRepository;
import dev.gmorikawa.toshokan.core.organization.OrganizationService;

public abstract class OrganizationTestEnvironment {

    @Autowired
    protected OrganizationRepository repository;

    @Autowired
    protected OrganizationService service;

    protected void clean(Organization organization) {
        repository.delete(organization);
    }
}