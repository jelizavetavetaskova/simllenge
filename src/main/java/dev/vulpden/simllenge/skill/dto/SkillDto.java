package dev.vulpden.simllenge.skill.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkillDto {
    private int skillId;
    private String name;
    private int levelCap;
}
