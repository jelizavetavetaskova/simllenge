package dev.vulpden.simllenge.sim.service;

import dev.vulpden.simllenge.career.dto.CareerDto;
import dev.vulpden.simllenge.sim.dto.AddSimCareerDto;
import dev.vulpden.simllenge.sim.dto.SimCareerDto;

import java.util.List;

public interface SimCareerService {
    List<CareerDto> getSuggestedCareers(int simId, String email);

    SimCareerDto addCareer(int simId, AddSimCareerDto dto, String email);
}
