package dev.vulpden.simllenge.general.service;

import dev.vulpden.simllenge.familyRole.dto.FamilyRoleDto;
import dev.vulpden.simllenge.familyRole.model.FamilyRole;
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
}
