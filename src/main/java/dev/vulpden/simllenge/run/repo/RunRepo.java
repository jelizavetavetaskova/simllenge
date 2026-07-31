package dev.vulpden.simllenge.run.repo;

import dev.vulpden.simllenge.run.model.Run;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunRepo extends JpaRepository<Run, Integer> {
}
