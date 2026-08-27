package dev.vulpden.simllenge.sim.controller;

import dev.vulpden.simllenge.career.dto.CareerDto;
import dev.vulpden.simllenge.sim.dto.AddSimCareerDto;
import dev.vulpden.simllenge.sim.dto.SimCareerDto;
import dev.vulpden.simllenge.sim.dto.UpdateSimCareerDto;
import dev.vulpden.simllenge.sim.service.SimCareerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sims/{simId}/careers")
public class SimCareerController {
    private final SimCareerService simCareerService;

    public SimCareerController(SimCareerService simCareerService) {
        this.simCareerService = simCareerService;
    }

    @GetMapping("/suggested")
    public ResponseEntity<List<CareerDto>> getSuggestedCareers(@PathVariable int simId, Authentication auth) {
        List<CareerDto> careers = simCareerService.getSuggestedCareers(simId, auth.getName());
        return ResponseEntity.ok(careers);
    }

    @PostMapping
    public ResponseEntity<SimCareerDto> addCareer(@PathVariable int simId, @Valid @RequestBody AddSimCareerDto dto, Authentication auth) {
        SimCareerDto career = simCareerService.addCareer(simId, dto, auth.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(career);
    }

    @PatchMapping("/{simCareerId}")
    public ResponseEntity<SimCareerDto> updateSimCareerLevel(@PathVariable int simId, @PathVariable int simCareerId, @Valid @RequestBody UpdateSimCareerDto dto, Authentication auth) {
        SimCareerDto career = simCareerService.updateCareerLevel(simId, simCareerId, auth.getName(), dto);
        return ResponseEntity.ok(career);
    }
}
