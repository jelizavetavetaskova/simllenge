package dev.vulpden.simllenge.trait.repo;

import dev.vulpden.simllenge.trait.model.Trait;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraitRepo extends JpaRepository<Trait, Integer> {
}
