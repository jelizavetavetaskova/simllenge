package dev.vulpden.simllenge.requirement.repo;

import dev.vulpden.simllenge.requirement.model.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequirementRepo extends JpaRepository<Requirement, Integer> {
}
