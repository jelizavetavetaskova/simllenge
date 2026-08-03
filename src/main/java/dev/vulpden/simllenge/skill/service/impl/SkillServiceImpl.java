package dev.vulpden.simllenge.skill.service.impl;

import dev.vulpden.simllenge.general.service.MapperService;
import dev.vulpden.simllenge.skill.dto.SkillDto;
import dev.vulpden.simllenge.skill.repo.SkillRepo;
import dev.vulpden.simllenge.skill.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {
    private final SkillRepo skillRepo;
    private final MapperService mapperService;

    public SkillServiceImpl(SkillRepo skillRepo, MapperService mapperService) {
        this.skillRepo = skillRepo;
        this.mapperService = mapperService;
    }

    @Override
    public List<SkillDto> getAllSkills() {
        return skillRepo.findAll()
                .stream()
                .map(mapperService::skillToDto)
                .toList();
    }
}
