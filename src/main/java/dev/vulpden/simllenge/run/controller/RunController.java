package dev.vulpden.simllenge.run.controller;

import dev.vulpden.simllenge.run.dto.CreateRunDto;
import dev.vulpden.simllenge.run.dto.RunDto;
import dev.vulpden.simllenge.run.service.RunService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/challenges/{challengeId}/runs")
public class RunController {
    private final RunService runService;

    public RunController(RunService runService) {
        this.runService = runService;
    }

    @PostMapping
    public ResponseEntity<Object> createRun(@PathVariable int challengeId, @Valid @RequestBody CreateRunDto runDto) {
        RunDto createdRun = runService.createRun(challengeId, runDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRun);
    }
}
