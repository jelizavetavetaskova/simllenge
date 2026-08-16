package dev.vulpden.simllenge.sim.controller;

import dev.vulpden.simllenge.sim.dto.SimDto;
import dev.vulpden.simllenge.sim.dto.UpdateSimDto;
import dev.vulpden.simllenge.sim.service.SimService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sims")
public class SimController {
    private final SimService simService;

    public SimController(SimService simService) {
        this.simService = simService;
    }

    @PutMapping("/{simId}")
    public ResponseEntity<SimDto> updateSim(@PathVariable int simId, @Valid @RequestBody UpdateSimDto simDto, Authentication auth) {
        String email = auth.getName();
        SimDto updatedSim = simService.updateSim(simId, simDto, email);
        return ResponseEntity.ok(updatedSim);
    }

    @PostMapping("/{simId}/kill")
    public ResponseEntity<SimDto> killSim(@PathVariable int simId, Authentication auth) {
        String email = auth.getName();
        SimDto killed = simService.markSimAsDead(simId, email);
        return ResponseEntity.ok(killed);
    }

    @PostMapping("/{simId}/age-up")
    public ResponseEntity<SimDto> ageUp(@PathVariable int simId, Authentication auth) {
        String email = auth.getName();
        SimDto aged = simService.ageUp(simId, email);
        return ResponseEntity.ok(aged);
    }
}
