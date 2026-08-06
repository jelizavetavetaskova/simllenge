package dev.vulpden.simllenge.stage.repo;

import dev.vulpden.simllenge.stage.model.Stage;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface StageRepo extends JpaRepository<Stage, Integer> {
    List<Stage> findAllByChallengeChallengeId(int challengeId, Sort stageOrder);
}
