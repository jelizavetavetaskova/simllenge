package dev.vulpden.simllenge.trait.service.impl;

import dev.vulpden.simllenge.general.service.MapperService;
import dev.vulpden.simllenge.trait.dto.TraitDto;
import dev.vulpden.simllenge.trait.repo.TraitRepo;
import dev.vulpden.simllenge.trait.service.TraitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraitServiceImpl implements TraitService {
    private final TraitRepo traitRepo;
    private final MapperService mapperService;

    public TraitServiceImpl(TraitRepo traitRepo, MapperService mapperService) {
        this.traitRepo = traitRepo;
        this.mapperService = mapperService;
    }

    @Override
    public List<TraitDto> getAllTraits() {
        return traitRepo.findAll().stream().map(mapperService::traitToDto).toList();
    }
}
