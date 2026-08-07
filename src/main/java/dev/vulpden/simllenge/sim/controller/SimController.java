package dev.vulpden.simllenge.sim.controller;

import dev.vulpden.simllenge.sim.dto.CreateSimDto;
import dev.vulpden.simllenge.sim.dto.SimDto;
import dev.vulpden.simllenge.sim.service.SimService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/challenges/{challengeId}/runs/{runId}/sims")
public class SimController {
    private final SimService simService;

    public SimController(SimService simService) {
        this.simService = simService;
    }

    @GetMapping
    public ResponseEntity<List<SimDto>> getSims(@PathVariable int runId) {
        List<SimDto> sims = simService.getSimsByRun(runId);
        return ResponseEntity.ok(sims);
    }

    @PostMapping
    public ResponseEntity<SimDto> createSim(@PathVariable int runId, @Valid @RequestBody CreateSimDto simDto) {
        SimDto sim = simService.createSim(runId, simDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(sim);
    }
}
