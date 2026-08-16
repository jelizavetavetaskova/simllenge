package dev.vulpden.simllenge.run.controller;

import dev.vulpden.simllenge.run.dto.RunDto;
import dev.vulpden.simllenge.run.service.RunService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/runs")
public class RunController {
    private final RunService runService;

    public RunController(RunService runService) {
        this.runService = runService;
    }

    @GetMapping("/{runId}")
    public ResponseEntity<RunDto> getRun(@PathVariable int runId, Authentication auth) {
        String email = auth.getName();
        return ResponseEntity.ok(runService.getRunById(runId, email));
    }
}
