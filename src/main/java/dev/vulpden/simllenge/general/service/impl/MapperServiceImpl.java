package dev.vulpden.simllenge.general.service.impl;

import dev.vulpden.simllenge.general.service.MapperService;
import dev.vulpden.simllenge.skill.dto.SkillDto;
import dev.vulpden.simllenge.skill.model.Skill;
import dev.vulpden.simllenge.stage.dto.StageDto;
import dev.vulpden.simllenge.stage.model.Stage;
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
}
