package dev.vulpden.simllenge.sim.service.impl;

import dev.vulpden.simllenge.familyRole.model.FamilyRole;
import dev.vulpden.simllenge.familyRole.repo.FamilyRoleRepo;
import dev.vulpden.simllenge.general.service.MapperService;
import dev.vulpden.simllenge.run.model.Run;
import dev.vulpden.simllenge.run.repo.RunRepo;
import dev.vulpden.simllenge.sim.dto.CreateSimDto;
import dev.vulpden.simllenge.sim.dto.SimDto;
import dev.vulpden.simllenge.sim.dto.UpdateSimDto;
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
    public List<SimDto> getSimsByRun(int runId, String email) {
        Run run = runRepo.findById(runId)
                .orElseThrow(() -> new NoSuchElementException("Run does not exist"));

        if (!run.getUser().getEmail().equals(email)) {
            throw new NoSuchElementException("User does not have a run with this id");
        }

        return simRepo.findAllByRunRunId(runId)
                .stream()
                .map(mapperService::simToDto)
                .toList();
    }

    @Override
    public SimDto createSim(int runId, CreateSimDto simDto, String email) {
        Run run = runRepo.findById(runId)
                .orElseThrow(() -> new NoSuchElementException("Run does not exist"));

        if (!run.getUser().getEmail().equals(email)) {
            throw new NoSuchElementException("User does not have a run with this id");
        }

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

    @Override
    public SimDto updateSim(int simId, UpdateSimDto simDto, String email) {
        Sim sim = simRepo.findById(simId)
                .orElseThrow(() -> new NoSuchElementException("Sim does not exist"));

        if (!sim.getRun().getUser().getEmail().equals(email)) {
            throw new NoSuchElementException("User does not have a run with this id");
        }

        FamilyRole familyRole = familyRoleRepo.findById(simDto.getFamilyRoleId())
                        .orElseThrow(() -> new NoSuchElementException("Family role does not exist"));

        sim.setName(simDto.getName());
        sim.setLifeStage(simDto.getLifeStage());
        sim.setFamilyRole(familyRole);
        return mapperService.simToDto(simRepo.save(sim));
    }

    @Override
    public SimDto markSimAsDead(int simId, String email) {
        Sim sim = simRepo.findById(simId)
                .orElseThrow(() -> new NoSuchElementException("Sim does not exist"));

        if (!sim.getRun().getUser().getEmail().equals(email)) {
            throw new NoSuchElementException("User does not have a run with this id");
        }

        if (!sim.isAlive()) return mapperService.simToDto(sim);

        sim.setAlive(false);
        return mapperService.simToDto(simRepo.save(sim));
    }

    @Override
    public SimDto ageUp(int simId, String email) {
        Sim sim = simRepo.findById(simId)
                .orElseThrow(() -> new NoSuchElementException("Sim does not exist"));

        if (!sim.getRun().getUser().getEmail().equals(email)) {
            throw new NoSuchElementException("User does not have a run with this id");
        }

        if (!sim.isAlive()) throw new IllegalStateException("Dead sim cannot age up");

        sim.setLifeStage(sim.getLifeStage().next());
        return mapperService.simToDto(simRepo.save(sim));
    }
}
