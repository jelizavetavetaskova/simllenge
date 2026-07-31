package dev.vulpden.simllenge.skill.repo;

import dev.vulpden.simllenge.skill.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepo extends JpaRepository<Skill, Integer> {
}
