package dev.vulpden.simllenge.challenge.service.impl;

import dev.vulpden.simllenge.challenge.dto.ChallengeDto;
import dev.vulpden.simllenge.challenge.repo.ChallengeRepo;
import dev.vulpden.simllenge.challenge.service.ChallengeService;
import dev.vulpden.simllenge.general.service.MapperService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeServiceImpl implements ChallengeService {
    private final ChallengeRepo challengeRepo;

    private final MapperService mapperService;

    public ChallengeServiceImpl(ChallengeRepo challengeRepo, MapperService mapperService) {
        this.challengeRepo = challengeRepo;
        this.mapperService = mapperService;
    }

    @Override
    public List<ChallengeDto> getAllChallenges() {
        return challengeRepo.findAll()
                .stream()
                .map(mapperService::challengeToDto)
                .toList();
    }
}
