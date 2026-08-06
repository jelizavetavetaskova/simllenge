package dev.vulpden.simllenge.stage.service;

import dev.vulpden.simllenge.stage.dto.StageDto;

import java.util.List;

public interface StageService {
    List<StageDto> getChallengeStages(int challengeId);
}
