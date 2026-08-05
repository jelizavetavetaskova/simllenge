package dev.vulpden.simllenge.run.repo;

import dev.vulpden.simllenge.run.model.Run;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RunRepo extends JpaRepository<Run, Integer> {
    List<Run> findAllByChallengeChallengeId(int challengeId);
}
