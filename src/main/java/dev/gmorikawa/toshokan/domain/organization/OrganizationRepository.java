package dev.gmorikawa.toshokan.domain.organization;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
    Optional<Organization> findByName(String name);
}
