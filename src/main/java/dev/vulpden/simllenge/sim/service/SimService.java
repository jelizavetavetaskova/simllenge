package dev.vulpden.simllenge.sim.service;

import dev.vulpden.simllenge.sim.dto.CreateSimDto;
import dev.vulpden.simllenge.sim.dto.SimDto;

import java.util.List;

public interface SimService {
    List<SimDto> getSimsByRun(int challengeId, int runId);

    SimDto createSim(int challengeId, int runId, CreateSimDto simDto);
}
