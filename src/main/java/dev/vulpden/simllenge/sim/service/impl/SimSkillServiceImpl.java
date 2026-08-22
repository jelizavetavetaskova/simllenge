package dev.vulpden.simllenge.sim.service.impl;

import dev.vulpden.simllenge.general.service.MapperService;
import dev.vulpden.simllenge.requirement.model.Requirement;
import dev.vulpden.simllenge.requirement.model.enums.MetricType;
import dev.vulpden.simllenge.requirement.model.enums.Scope;
import dev.vulpden.simllenge.run.model.Run;
import dev.vulpden.simllenge.sim.dto.AddSimSkillDto;
import dev.vulpden.simllenge.sim.dto.SimSkillDto;
import dev.vulpden.simllenge.sim.dto.UpdateSimSkillDto;
import dev.vulpden.simllenge.sim.model.Sim;
import dev.vulpden.simllenge.sim.model.SimSkill;
import dev.vulpden.simllenge.sim.repo.SimRepo;
import dev.vulpden.simllenge.sim.repo.SimSkillRepo;
import dev.vulpden.simllenge.sim.service.SimSkillService;
import dev.vulpden.simllenge.skill.dto.SkillDto;
import dev.vulpden.simllenge.skill.model.Skill;
import dev.vulpden.simllenge.skill.repo.SkillRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public List<SkillDto> getSuggestedSkills(int simId, String email) {
        Sim sim = simRepo.findById(simId)
                .orElseThrow(() -> new NoSuchElementException("Sim does not exist"));

        if (!sim.getRun().getUser().getEmail().equals(email)) {
            throw new NoSuchElementException("User does not have a run with this id");
        }

        Run run = sim.getRun();
        List<Requirement> requirements = run.getStage().getRequirements()
                .stream()
                .filter(r -> r.getMetricType() == MetricType.SKILL)
                .toList();

        Set<Skill> skills = new HashSet<>();
        for (Requirement requirement: requirements) {
            if (requirement.getScope() == Scope.ROLE) {
                if (sim.getFamilyRole().getFamilyRoleId() == requirement.getRole().getFamilyRoleId()) {
                    skills.addAll(requirement.getSkills());
                }
            } else if (requirement.getScope() == Scope.EACH_MEMBER) {
                if (sim.getLifeStage().ordinal() >= requirement.getMinLifeStage().ordinal()) {
                    skills.addAll(requirement.getSkills());
                }
            }
        }

        Set<Integer> ownedSimSkills = sim.getSkills()
                .stream()
                .map(skill -> skill.getSkill().getSkillId())
                .collect(Collectors.toSet());

        return skills.stream()
                .filter(skill -> !ownedSimSkills.contains(skill.getSkillId()))
                .map(mapperService::skillToDto)
                .toList();
    }

    @Override
    public SimSkillDto updateSkillLevel(int simSkillId, int simId, String email, UpdateSimSkillDto dto) {
        Sim sim = simRepo.findById(simId)
                .orElseThrow(() -> new NoSuchElementException("Sim does not exist"));
        if (!sim.getRun().getUser().getEmail().equals(email))
            throw new NoSuchElementException("User does not have a run with this id");

        SimSkill skill = simSkillRepo.findById(simSkillId)
                .orElseThrow(() -> new NoSuchElementException("Skill does not exist"));
        if (skill.getSim().getSimId() != sim.getSimId())
            throw new IllegalArgumentException("Skill does not belong to this sim");

        if (dto.getLevel() > skill.getSkill().getLevelCap())
            throw new IllegalArgumentException("Level must be from 1 to " + skill.getSkill().getLevelCap());

        skill.setLevel(dto.getLevel());
        skill.setUpdatedAt(LocalDateTime.now());

        return mapperService.simSkillToDto(simSkillRepo.save(skill));
    }

    @Override
    public void removeSkill(int simSkillId, int simId, String email) {
        Sim sim = simRepo.findById(simId)
                .orElseThrow(() -> new NoSuchElementException("Sim does not exist"));
        if (!sim.getRun().getUser().getEmail().equals(email))
            throw new NoSuchElementException("User does not have a run with this id");

        SimSkill skill = simSkillRepo.findById(simSkillId)
                .orElseThrow(() -> new NoSuchElementException("Skill does not exist"));
        if (skill.getSim().getSimId() != sim.getSimId())
            throw new IllegalArgumentException("Skill does not belong to this sim");

        simSkillRepo.delete(skill);
    }
}
