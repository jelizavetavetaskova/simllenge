package dev.vulpden.simllenge.run.service;

import dev.vulpden.simllenge.run.dto.CreateRunDto;
import dev.vulpden.simllenge.run.dto.RunDto;

public interface RunService {
    RunDto createRun(int challengeId, CreateRunDto runDto);
}
