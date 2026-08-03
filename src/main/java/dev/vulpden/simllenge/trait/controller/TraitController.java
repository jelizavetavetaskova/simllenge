package dev.vulpden.simllenge.trait.controller;

import dev.vulpden.simllenge.trait.dto.TraitDto;
import dev.vulpden.simllenge.trait.service.TraitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/traits")
public class TraitController {
    private final TraitService traitService;

    public TraitController(TraitService traitService) {
        this.traitService = traitService;
    }

    @GetMapping
    public List<TraitDto> getTraits() {
        return traitService.getAllTraits();
    }
}
