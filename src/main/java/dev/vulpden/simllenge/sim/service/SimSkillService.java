package dev.vulpden.simllenge.sim.service;

import dev.vulpden.simllenge.sim.dto.AddSimSkillDto;
import dev.vulpden.simllenge.sim.dto.SimSkillDto;
import dev.vulpden.simllenge.sim.dto.UpdateSimSkillDto;
import dev.vulpden.simllenge.skill.dto.SkillDto;

import java.util.List;

public interface SimSkillService {
    SimSkillDto addSimSkill(int simId, AddSimSkillDto dto, String email);

    List<SkillDto> getSuggestedSkills(int simId, String email);

    SimSkillDto updateSkillLevel(int simSkillId, int simId, String email, UpdateSimSkillDto dto);

    void removeSkill(int simSkillId, int simId, String email);
}
