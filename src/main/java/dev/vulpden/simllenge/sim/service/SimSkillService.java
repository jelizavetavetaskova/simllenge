package dev.vulpden.simllenge.sim.service;

import dev.vulpden.simllenge.sim.dto.AddSimSkillDto;
import dev.vulpden.simllenge.sim.dto.SimSkillDto;

public interface SimSkillService {
    SimSkillDto addSimSkill(int simId, AddSimSkillDto dto, String email);
}
