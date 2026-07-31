package dev.vulpden.simllenge.career.repo;

import dev.vulpden.simllenge.career.model.Career;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerRepo extends JpaRepository<Career, Integer> {
}
