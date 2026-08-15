package dev.vulpden.simllenge.challenge.controller;

import dev.vulpden.simllenge.challenge.dto.ChallengeDto;
import dev.vulpden.simllenge.challenge.service.ChallengeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {
    private final ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping
    public ResponseEntity<List<ChallengeDto>> getAllChallenges() {
        List<ChallengeDto> challenges = challengeService.getAllChallenges();
        return ResponseEntity.ok(challenges);
    }
}
