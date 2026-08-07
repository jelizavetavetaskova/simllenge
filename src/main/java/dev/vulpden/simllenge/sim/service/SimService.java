package dev.vulpden.simllenge.sim.service;

import dev.vulpden.simllenge.sim.dto.CreateSimDto;
import dev.vulpden.simllenge.sim.dto.SimDto;

import java.util.List;

public interface SimService {
    List<SimDto> getSimsByRun(int runId);

    SimDto createSim(int runId, CreateSimDto simDto);
}
