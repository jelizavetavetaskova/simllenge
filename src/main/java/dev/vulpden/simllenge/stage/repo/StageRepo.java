package dev.vulpden.simllenge.stage.repo;

import dev.vulpden.simllenge.stage.model.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StageRepo extends JpaRepository<Stage, Integer> {
}
