package dev.vulpden.simllenge.sim.dto;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddSimSkillDto {
    @Positive(message = "Skill id must be positive")
    private int skillId;
}
