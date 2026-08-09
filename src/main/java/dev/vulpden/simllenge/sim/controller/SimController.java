package dev.vulpden.simllenge.sim.controller;

import dev.vulpden.simllenge.sim.dto.SimDto;
import dev.vulpden.simllenge.sim.dto.UpdateSimDto;
import dev.vulpden.simllenge.sim.service.SimService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sims")
public class SimController {
    private final SimService simService;

    public SimController(SimService simService) {
        this.simService = simService;
    }

    @PutMapping("/{simId}")
    public ResponseEntity<SimDto> updateSim(@PathVariable int simId, @Valid @RequestBody UpdateSimDto simDto) {
        SimDto updatedSim = simService.updateSim(simId, simDto);
        return ResponseEntity.ok(updatedSim);
    }
}
