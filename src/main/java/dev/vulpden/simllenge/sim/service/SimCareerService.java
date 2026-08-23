package dev.vulpden.simllenge.sim.service;

import dev.vulpden.simllenge.career.dto.CareerDto;

import java.util.List;

public interface SimCareerService {
    List<CareerDto> getSuggestedCareers(int simId, String email);
}
