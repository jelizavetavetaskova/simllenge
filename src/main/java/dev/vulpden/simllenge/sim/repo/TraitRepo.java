package dev.vulpden.simllenge.sim.repo;

import dev.vulpden.simllenge.sim.model.Trait;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraitRepo extends JpaRepository<Trait, Integer> {
}
