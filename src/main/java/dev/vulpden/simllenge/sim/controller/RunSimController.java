package dev.vulpden.simllenge.sim.controller;

import dev.vulpden.simllenge.sim.dto.CreateSimDto;
import dev.vulpden.simllenge.sim.dto.SimDto;
import dev.vulpden.simllenge.sim.service.SimService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/runs/{runId}/sims")
public class RunSimController {
    private final SimService simService;

    public RunSimController(SimService simService) {
        this.simService = simService;
    }

    @GetMapping
    public ResponseEntity<List<SimDto>> getSims(@PathVariable int runId, Authentication auth) {
        String email = auth.getName();
        List<SimDto> sims = simService.getSimsByRun(runId, email);
        return ResponseEntity.ok(sims);
    }

    @PostMapping
    public ResponseEntity<SimDto> createSim(@PathVariable int runId, @Valid @RequestBody CreateSimDto simDto, Authentication auth) {
        String email = auth.getName();
        SimDto sim = simService.createSim(runId, simDto, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(sim);
    }
}
