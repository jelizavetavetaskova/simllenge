package dev.vulpden.simllenge.sim.controller;

import dev.vulpden.simllenge.sim.dto.AddSimSkillDto;
import dev.vulpden.simllenge.sim.dto.SimSkillDto;
import dev.vulpden.simllenge.sim.service.SimSkillService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sims/{simId}/skills")
public class SimSkillController {
    private final SimSkillService simSkillService;

    public SimSkillController(SimSkillService simSkillService) {
        this.simSkillService = simSkillService;
    }

    @PostMapping
    public ResponseEntity<SimSkillDto> addSimSkill(@PathVariable int simId, @Valid @RequestBody AddSimSkillDto dto, Authentication auth) {
        SimSkillDto simSkill = simSkillService.addSimSkill(simId, dto, auth.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(simSkill);
    }
}
