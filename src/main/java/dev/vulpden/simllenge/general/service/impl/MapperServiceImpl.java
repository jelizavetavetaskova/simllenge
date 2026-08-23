package dev.vulpden.simllenge.general.service.impl;

import dev.vulpden.simllenge.career.dto.CareerBranchDto;
import dev.vulpden.simllenge.career.dto.CareerDto;
import dev.vulpden.simllenge.career.model.Career;
import dev.vulpden.simllenge.career.model.CareerBranch;
import dev.vulpden.simllenge.challenge.dto.ChallengeDto;
import dev.vulpden.simllenge.challenge.model.Challenge;
import dev.vulpden.simllenge.familyRole.dto.FamilyRoleDto;
import dev.vulpden.simllenge.familyRole.model.FamilyRole;
import dev.vulpden.simllenge.general.service.MapperService;
import dev.vulpden.simllenge.run.dto.RunDto;
import dev.vulpden.simllenge.run.model.Run;
import dev.vulpden.simllenge.sim.dto.SimDto;
import dev.vulpden.simllenge.sim.dto.SimSkillDto;
import dev.vulpden.simllenge.sim.model.Sim;
import dev.vulpden.simllenge.sim.model.SimSkill;
import dev.vulpden.simllenge.skill.dto.SkillDto;
import dev.vulpden.simllenge.skill.model.Skill;
import dev.vulpden.simllenge.stage.dto.StageDto;
import dev.vulpden.simllenge.stage.model.Stage;
import dev.vulpden.simllenge.trait.dto.TraitDto;
import dev.vulpden.simllenge.trait.model.Trait;
import dev.vulpden.simllenge.user.dto.UserDto;
import dev.vulpden.simllenge.user.model.User;
import org.springframework.stereotype.Service;

@Service
public class MapperServiceImpl implements MapperService {
    @Override
    public SkillDto skillToDto(Skill skill) {
        SkillDto dto = new SkillDto();
        dto.setSkillId(skill.getSkillId());
        dto.setName(skill.getName());
        dto.setLevelCap(skill.getLevelCap());
        return dto;
    }

    @Override
    public StageDto stageToDto(Stage stage) {
        StageDto dto = new StageDto();
        dto.setStageId(stage.getStageId());
        dto.setName(stage.getName());
        dto.setStageOrder(stage.getStageOrder());
        dto.setTaxApplies(stage.isTaxApplies());
        return dto;
    }

    @Override
    public TraitDto traitToDto(Trait trait) {
        TraitDto dto = new TraitDto();
        dto.setTraitId(trait.getTraitId());
        dto.setName(trait.getName());
        return dto;
    }

    @Override
    public FamilyRoleDto familyRoleToDto(FamilyRole familyRole) {
        FamilyRoleDto dto = new FamilyRoleDto();
        dto.setFamilyRoleId(familyRole.getFamilyRoleId());
        dto.setName(familyRole.getName());
        return dto;
    }

    @Override
    public CareerDto careerToDto(Career career) {
        CareerDto dto = new CareerDto();
        dto.setCareerId(career.getCareerId());
        dto.setName(career.getName());
        dto.setCareerType(career.getCareerType());
        dto.setCareerBranches(career.getCareerBranches()
                .stream()
                .map(this::careerBranchToDto)
                .toList());
        return dto;
    }

    @Override
    public CareerBranchDto careerBranchToDto(CareerBranch careerBranch) {
        CareerBranchDto dto = new CareerBranchDto();
        dto.setCareerBranchId(careerBranch.getCareerBranchId());
        dto.setName(careerBranch.getName());
        return dto;
    }

    @Override
    public RunDto runToDto(Run run) {
        RunDto dto = new RunDto();
        dto.setRunId(run.getRunId());
        dto.setBudget(run.getBudget());
        dto.setStage(stageToDto(run.getStage()));
        dto.setChallengeTitle(run.getChallenge().getTitle());
        return dto;
    }

    @Override
    public SimDto simToDto(Sim sim) {
        SimDto dto = new SimDto();
        dto.setSimId(sim.getSimId());
        dto.setName(sim.getName());
        dto.setFamilyRole(familyRoleToDto(sim.getFamilyRole()));
        dto.setLifeStage(sim.getLifeStage());
        dto.setAlive(sim.isAlive());
        dto.setSkills(sim.getSkills().stream().map(this::simSkillToDto).toList());
        return dto;
    }

    @Override
    public UserDto userToDto(User user) {
        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setAuthority(user.getAuthority());
        return dto;
    }

    @Override
    public ChallengeDto challengeToDto(Challenge challenge) {
        ChallengeDto dto = new ChallengeDto();
        dto.setChallengeId(challenge.getChallengeId());
        dto.setTitle(challenge.getTitle());
        return dto;
    }

    @Override
    public SimSkillDto simSkillToDto(SimSkill simSkill) {
        SimSkillDto dto = new SimSkillDto();
        dto.setSimSkillId(simSkill.getSimSkillId());
        dto.setSkill(skillToDto(simSkill.getSkill()));
        dto.setLevel(simSkill.getLevel());
        dto.setUpdatedAt(simSkill.getUpdatedAt());
        return dto;
    }
}
