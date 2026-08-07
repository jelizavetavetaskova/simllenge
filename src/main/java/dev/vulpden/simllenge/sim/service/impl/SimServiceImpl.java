package dev.vulpden.simllenge.sim.service.impl;

import dev.vulpden.simllenge.familyRole.model.FamilyRole;
import dev.vulpden.simllenge.familyRole.repo.FamilyRoleRepo;
import dev.vulpden.simllenge.general.service.MapperService;
import dev.vulpden.simllenge.run.model.Run;
import dev.vulpden.simllenge.run.repo.RunRepo;
import dev.vulpden.simllenge.sim.dto.CreateSimDto;
import dev.vulpden.simllenge.sim.dto.SimDto;
import dev.vulpden.simllenge.sim.model.Sim;
import dev.vulpden.simllenge.sim.repo.SimRepo;
import dev.vulpden.simllenge.sim.service.SimService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SimServiceImpl implements SimService {
    private final SimRepo simRepo;
    private final RunRepo runRepo;
    private final FamilyRoleRepo familyRoleRepo;

    private final MapperService mapperService;

    public SimServiceImpl(SimRepo simRepo, RunRepo runRepo, FamilyRoleRepo familyRoleRepo, MapperService mapperService) {
        this.simRepo = simRepo;
        this.runRepo = runRepo;
        this.familyRoleRepo = familyRoleRepo;
        this.mapperService = mapperService;
    }

    @Override
    public List<SimDto> getSimsByRun(int runId) {
        if (!runRepo.existsById(runId)) throw new NoSuchElementException("Run does not exist");

        return simRepo.findAllByRunRunId(runId)
                .stream()
                .map(mapperService::simToDto)
                .toList();
    }

    @Override
    public SimDto createSim(int runId, CreateSimDto simDto) {
        Run run = runRepo.findById(runId)
                .orElseThrow(() -> new NoSuchElementException("Run does not exist"));
        FamilyRole familyRole = familyRoleRepo.findById(simDto.getFamilyRoleId())
                .orElseThrow(() -> new NoSuchElementException("Family role does not exist"));

        Sim sim = new Sim();
        sim.setName(simDto.getName());
        sim.setAlive(true);
        sim.setRun(run);
        sim.setFamilyRole(familyRole);
        sim.setLifeStage(simDto.getLifeStage());

        return mapperService.simToDto(simRepo.save(sim));
    }
}
