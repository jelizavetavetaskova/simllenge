package dev.vulpden.simllenge.run.service;

import dev.vulpden.simllenge.run.dto.CreateRunDto;
import dev.vulpden.simllenge.run.dto.RunDto;

import java.util.List;

public interface RunService {
    List<RunDto> getChallengeRuns(int challengeId, String email);

    RunDto getRunById(int runId, String email);

    RunDto createRun(int challengeId, CreateRunDto runDto, String email);
}
