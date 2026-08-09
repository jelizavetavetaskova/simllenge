package dev.vulpden.simllenge.run.controller;

import dev.vulpden.simllenge.run.dto.CreateRunDto;
import dev.vulpden.simllenge.run.dto.RunDto;
import dev.vulpden.simllenge.run.service.RunService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/runs")
public class RunController {
    private final RunService runService;

    public RunController(RunService runService) {
        this.runService = runService;
    }

    @GetMapping("/{runId}")
    public ResponseEntity<RunDto> getRun(@PathVariable int runId) {
        return ResponseEntity.ok(runService.getRunById(runId));
    }
}
