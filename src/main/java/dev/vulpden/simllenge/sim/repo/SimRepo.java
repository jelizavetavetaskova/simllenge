package dev.vulpden.simllenge.sim.repo;

import dev.vulpden.simllenge.sim.model.Sim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimRepo extends JpaRepository<Sim, Integer> {
    List<Sim> findAllByRunRunId(int runId);
}
