package dev.vulpden.simllenge.run.controller;

import dev.vulpden.simllenge.run.dto.CreateRunDto;
import dev.vulpden.simllenge.run.dto.RunDto;
import dev.vulpden.simllenge.run.service.RunService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/challenges/{challengeId}/runs")
public class ChallengeRunController {
    private final RunService runService;

    public ChallengeRunController(RunService runService) {
        this.runService = runService;
    }

    @GetMapping
    public ResponseEntity<List<RunDto>> getRuns(@PathVariable int challengeId) {
        List<RunDto> runs = runService.getChallengeRuns(challengeId);
        return ResponseEntity.ok(runs);
    }

    @PostMapping
    public ResponseEntity<Object> createRun(@PathVariable int challengeId, @Valid @RequestBody CreateRunDto runDto, Authentication auth) {
        String email = auth.getName();
        RunDto createdRun = runService.createRun(challengeId, runDto, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRun);
    }
}
