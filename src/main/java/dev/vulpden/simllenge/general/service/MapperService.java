package dev.vulpden.simllenge.general.service;

import dev.vulpden.simllenge.skill.dto.SkillDto;
import dev.vulpden.simllenge.skill.model.Skill;

public interface MapperService {
    SkillDto skillToDto(Skill skill);
}
