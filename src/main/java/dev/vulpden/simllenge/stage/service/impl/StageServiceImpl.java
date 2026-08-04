package dev.vulpden.simllenge.stage.service.impl;

import dev.vulpden.simllenge.general.service.MapperService;
import dev.vulpden.simllenge.stage.dto.StageDto;
import dev.vulpden.simllenge.stage.repo.StageRepo;
import dev.vulpden.simllenge.stage.service.StageService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StageServiceImpl implements StageService {
    private final StageRepo stageRepo;
    private final MapperService mapperService;

    public StageServiceImpl(StageRepo stageRepo, MapperService mapperService) {
        this.stageRepo = stageRepo;
        this.mapperService = mapperService;
    }

    @Override
    public List<StageDto> getAllStages() {
        return stageRepo.findAll(Sort.by("stageOrder"))
                .stream()
                .map(mapperService::stageToDto)
                .toList();
    }
}
