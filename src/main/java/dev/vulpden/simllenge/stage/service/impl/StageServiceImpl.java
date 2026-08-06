package dev.vulpden.simllenge.stage.service.impl;

import dev.vulpden.simllenge.challenge.repo.ChallengeRepo;
import dev.vulpden.simllenge.general.service.MapperService;
import dev.vulpden.simllenge.stage.dto.StageDto;
import dev.vulpden.simllenge.stage.repo.StageRepo;
import dev.vulpden.simllenge.stage.service.StageService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StageServiceImpl implements StageService {
    private final StageRepo stageRepo;
    private final ChallengeRepo challengeRepo;

    private final MapperService mapperService;

    public StageServiceImpl(StageRepo stageRepo, ChallengeRepo challengeRepo, MapperService mapperService) {
        this.stageRepo = stageRepo;
        this.challengeRepo = challengeRepo;
        this.mapperService = mapperService;
    }

    @Override
    public List<StageDto> getChallengeStages(int challengeId) {
        if (!challengeRepo.existsById(challengeId)) throw new NoSuchElementException("Challenge does not exist");

        return stageRepo.findAllByChallengeChallengeId(challengeId, Sort.by("stageOrder"))
                .stream()
                .map(mapperService::stageToDto)
                .toList();
    }
}
