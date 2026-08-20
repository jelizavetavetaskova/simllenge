package dev.vulpden.simllenge.sim.dto;

import dev.vulpden.simllenge.skill.dto.SkillDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SimSkillDto {
    private int simSkillId;
    private SkillDto skill;
    private int level;
    private LocalDateTime updatedAt;
}
