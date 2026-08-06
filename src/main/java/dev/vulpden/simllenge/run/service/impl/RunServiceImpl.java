package dev.vulpden.simllenge.run.service.impl;

import dev.vulpden.simllenge.challenge.model.Challenge;
import dev.vulpden.simllenge.challenge.repo.ChallengeRepo;
import dev.vulpden.simllenge.general.service.MapperService;
import dev.vulpden.simllenge.run.dto.CreateRunDto;
import dev.vulpden.simllenge.run.dto.RunDto;
import dev.vulpden.simllenge.run.model.Run;
import dev.vulpden.simllenge.run.repo.RunRepo;
import dev.vulpden.simllenge.run.service.RunService;
import dev.vulpden.simllenge.stage.model.Stage;
import dev.vulpden.simllenge.stage.repo.StageRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RunServiceImpl implements RunService {
    private final ChallengeRepo challengeRepo;
    private final RunRepo runRepo;
    private final StageRepo stageRepo;

    private final MapperService mapperService;

    public RunServiceImpl(ChallengeRepo challengeRepo, RunRepo runRepo, StageRepo stageRepo, MapperService mapperService) {
        this.challengeRepo = challengeRepo;
        this.runRepo = runRepo;
        this.stageRepo = stageRepo;
        this.mapperService = mapperService;
    }

    @Override
    public List<RunDto> getChallengeRuns(int challengeId) {
        if (!challengeRepo.existsById(challengeId)) throw new NoSuchElementException("Challenge does not exist");

        return runRepo.findAllByChallengeChallengeId(challengeId)
                .stream()
                .map(mapperService::runToDto)
                .toList();
    }

    @Override
    public RunDto createRun(int challengeId, CreateRunDto runDto) {
        Challenge challenge = challengeRepo.findById(challengeId)
                .orElseThrow(() -> new NoSuchElementException("Challenge does not exist"));

        Stage stage = stageRepo.findById(runDto.getStageId())
                .orElseThrow(() -> new NoSuchElementException("Stage does not exist"));

        Run run = new Run();
        run.setChallenge(challenge);
        run.setBudget(runDto.getBudget());
        run.setStage(stage);

        return mapperService.runToDto(runRepo.save(run));
    }
}
