package dev.vulpden.simllenge.challenge.service;

import dev.vulpden.simllenge.challenge.dto.ChallengeDto;

import java.util.List;

public interface ChallengeService {
    List<ChallengeDto> getAllChallenges();
}
