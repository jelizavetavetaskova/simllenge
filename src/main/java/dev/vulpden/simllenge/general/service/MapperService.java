package dev.vulpden.simllenge.general.service;

import dev.vulpden.simllenge.career.dto.CareerBranchDto;
import dev.vulpden.simllenge.career.dto.CareerDto;
import dev.vulpden.simllenge.career.model.Career;
import dev.vulpden.simllenge.career.model.CareerBranch;
import dev.vulpden.simllenge.familyRole.dto.FamilyRoleDto;
import dev.vulpden.simllenge.familyRole.model.FamilyRole;
import dev.vulpden.simllenge.run.dto.RunDto;
import dev.vulpden.simllenge.run.model.Run;
import dev.vulpden.simllenge.skill.dto.SkillDto;
import dev.vulpden.simllenge.skill.model.Skill;
import dev.vulpden.simllenge.stage.dto.StageDto;
import dev.vulpden.simllenge.stage.model.Stage;
import dev.vulpden.simllenge.trait.dto.TraitDto;
import dev.vulpden.simllenge.trait.model.Trait;

public interface MapperService {
    SkillDto skillToDto(Skill skill);

    StageDto stageToDto(Stage stage);

    TraitDto traitToDto(Trait trait);

    FamilyRoleDto familyRoleToDto(FamilyRole familyRole);

    CareerDto careerToDto(Career career);

    CareerBranchDto careerBranchToDto(CareerBranch careerBranch);

    RunDto runToDto(Run run);
}
