package dev.vulpden.simllenge.sim.service;

import dev.vulpden.simllenge.sim.dto.CreateSimDto;
import dev.vulpden.simllenge.sim.dto.SimDto;
import dev.vulpden.simllenge.sim.dto.UpdateSimDto;

import java.util.List;

public interface SimService {
    List<SimDto> getSimsByRun(int runId, String email);

    SimDto createSim(int runId, CreateSimDto simDto, String email);

    SimDto updateSim(int simId, UpdateSimDto simDto, String email);

    SimDto markSimAsDead(int simId, String email);

    SimDto ageUp(int simId, String email);
}
