package dev.vulpden.simllenge.challenge.repo;

import dev.vulpden.simllenge.challenge.model.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepo extends JpaRepository<Challenge, Integer> {
}
