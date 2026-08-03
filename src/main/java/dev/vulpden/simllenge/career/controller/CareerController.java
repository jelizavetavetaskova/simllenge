package dev.vulpden.simllenge.career.controller;

import dev.vulpden.simllenge.career.dto.CareerDto;
import dev.vulpden.simllenge.career.service.CareerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/careers")
public class CareerController {
    private final CareerService careerService;

    public CareerController(CareerService careerService) {
        this.careerService = careerService;
    }

    @GetMapping
    public List<CareerDto> getCareers() {
        return careerService.getAllCareers();
    }
}
