package dev.vulpden.simllenge.skill.controller;

import dev.vulpden.simllenge.skill.dto.SkillDto;
import dev.vulpden.simllenge.skill.service.SkillService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public List<SkillDto> getSkills() {
        return skillService.getAllSkills();
    }
}
