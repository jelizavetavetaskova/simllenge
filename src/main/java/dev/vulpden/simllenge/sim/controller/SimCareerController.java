package dev.vulpden.simllenge.sim.controller;

import dev.vulpden.simllenge.career.dto.CareerDto;
import dev.vulpden.simllenge.sim.service.SimCareerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
