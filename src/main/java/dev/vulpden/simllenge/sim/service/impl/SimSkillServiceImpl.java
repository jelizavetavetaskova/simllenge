package dev.vulpden.simllenge.sim.service.impl;

import dev.vulpden.simllenge.general.service.MapperService;
import dev.vulpden.simllenge.sim.dto.AddSimSkillDto;
import dev.vulpden.simllenge.sim.dto.SimSkillDto;
import dev.vulpden.simllenge.sim.model.Sim;
import dev.vulpden.simllenge.sim.model.SimSkill;
import dev.vulpden.simllenge.sim.repo.SimRepo;
import dev.vulpden.simllenge.sim.repo.SimSkillRepo;
import dev.vulpden.simllenge.sim.service.SimSkillService;
import dev.vulpden.simllenge.skill.model.Skill;
import dev.vulpden.simllenge.skill.repo.SkillRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class SimSkillServiceImpl implements SimSkillService {
    private final SimRepo simRepo;
    private final SimSkillRepo simSkillRepo;
    private final SkillRepo skillRepo;

    private final MapperService mapperService;

    public SimSkillServiceImpl(SimRepo simRepo, SimSkillRepo simSkillRepo, SkillRepo skillRepo,
                               MapperService mapperService
    ) {
        this.simRepo = simRepo;
        this.simSkillRepo = simSkillRepo;
        this.skillRepo = skillRepo;
        this.mapperService = mapperService;
    }

    @Override
    public SimSkillDto addSimSkill(int simId, AddSimSkillDto dto, String email) {
        Sim sim = simRepo.findById(simId)
                .orElseThrow(() -> new NoSuchElementException("Sim does not exist"));

        if (!sim.getRun().getUser().getEmail().equals(email))
            throw new NoSuchElementException("User does not have a run with this id");

        Skill skill = skillRepo.findById(dto.getSkillId())
                .orElseThrow(() -> new NoSuchElementException("Skill does not exist"));

        if (simSkillRepo.existsBySimSimIdAndSkillSkillId(sim.getSimId(), dto.getSkillId()))
            throw new IllegalArgumentException("The sim already has this skill");

        SimSkill simSkill = new SimSkill();
        simSkill.setSim(sim);
        simSkill.setSkill(skill);
        simSkill.setLevel(1);
        simSkill.setUpdatedAt(LocalDateTime.now());


        return mapperService.simSkillToDto(simSkillRepo.save(simSkill));
    }
}
