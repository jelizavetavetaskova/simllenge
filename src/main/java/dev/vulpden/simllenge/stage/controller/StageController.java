package dev.vulpden.simllenge.stage.controller;

import dev.vulpden.simllenge.stage.dto.StageDto;
import dev.vulpden.simllenge.stage.service.StageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/challenges/{challengeId}/stages")
public class StageController {
    private final StageService stageService;

    public StageController(StageService stageService) {
        this.stageService = stageService;
    }

    @GetMapping
    public List<StageDto> getStages(@PathVariable int challengeId) {
        return stageService.getChallengeStages(challengeId);
    }
}
