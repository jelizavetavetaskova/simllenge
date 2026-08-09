package dev.vulpden.simllenge.run.service;

import dev.vulpden.simllenge.run.dto.CreateRunDto;
import dev.vulpden.simllenge.run.dto.RunDto;

import java.util.List;

public interface RunService {
    List<RunDto> getChallengeRuns(int challengeId);

    RunDto getRunById(int runId);

    RunDto createRun(int challengeId, CreateRunDto runDto);
}
