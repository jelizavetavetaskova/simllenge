package dev.vulpden.simllenge.career.repo;

import dev.vulpden.simllenge.career.model.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CareerRepo extends JpaRepository<Career, Integer> {
    @Query("SELECT DISTINCT c FROM Career c LEFT JOIN FETCH c.careerBranches")
    List<Career> findAllCareers();
}
