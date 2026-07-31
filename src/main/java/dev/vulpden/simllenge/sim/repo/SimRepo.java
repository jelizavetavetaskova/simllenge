package dev.vulpden.simllenge.sim.repo;

import dev.vulpden.simllenge.sim.model.Sim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimRepo extends JpaRepository<Sim, Integer> {
}
