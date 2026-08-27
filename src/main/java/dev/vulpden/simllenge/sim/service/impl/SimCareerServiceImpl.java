package dev.vulpden.simllenge.sim.service.impl;

import dev.vulpden.simllenge.career.dto.CareerDto;
import dev.vulpden.simllenge.career.model.Career;
import dev.vulpden.simllenge.career.model.CareerBranch;
import dev.vulpden.simllenge.career.model.enums.CareerType;
import dev.vulpden.simllenge.career.repo.CareerBranchRepo;
import dev.vulpden.simllenge.general.service.MapperService;
import dev.vulpden.simllenge.requirement.model.Requirement;
import dev.vulpden.simllenge.requirement.model.enums.MetricType;
import dev.vulpden.simllenge.run.model.Run;
import dev.vulpden.simllenge.sim.dto.AddSimCareerDto;
import dev.vulpden.simllenge.sim.dto.SimCareerDto;
import dev.vulpden.simllenge.sim.dto.UpdateSimCareerDto;
import dev.vulpden.simllenge.sim.model.Sim;
import dev.vulpden.simllenge.sim.model.SimCareer;
import dev.vulpden.simllenge.sim.model.enums.LifeStage;
import dev.vulpden.simllenge.sim.repo.SimCareerRepo;
import dev.vulpden.simllenge.sim.repo.SimRepo;
import dev.vulpden.simllenge.sim.service.SimCareerService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SimCareerServiceImpl implements SimCareerService {
    private final SimRepo simRepo;
    private final CareerBranchRepo careerBranchRepo;
    private final SimCareerRepo simCareerRepo;

    private final MapperService mapperService;

    public SimCareerServiceImpl(SimRepo simRepo, CareerBranchRepo careerBranchRepo, SimCareerRepo simCareerRepo,
                                MapperService mapperService) {
        this.simRepo = simRepo;
        this.careerBranchRepo = careerBranchRepo;
        this.simCareerRepo = simCareerRepo;

        this.mapperService = mapperService;
    }

    @Override
    public List<CareerDto> getSuggestedCareers(int simId, String email) {
        Sim sim = simRepo.findById(simId)
                .orElseThrow(() -> new NoSuchElementException("Sim does not exist"));

        Run run = sim.getRun();
        if (!run.getUser().getEmail().equals(email)) {
            throw new NoSuchElementException("User does not have a run with this id");
        }

        Set<Requirement> requirements = run.getStage().getRequirements()
                .stream()
                .filter(requirement -> requirement.getMetricType() == MetricType.CAREER)
                .collect(Collectors.toSet());

        Set<Career> careers = new HashSet<>();

        if ((sim.getLifeStage().ordinal() < LifeStage.YOUNG_ADULT.ordinal()) || (!sim.getCareers().isEmpty())) {
            return new ArrayList<>();
        }

        for (Requirement req: requirements) {
            careers.add(req.getCareerBranch().getCareer());
        }

        return careers
                .stream()
                .map(mapperService::careerToDto)
                .toList();
    }

    @Override
    public SimCareerDto addCareer(int simId, AddSimCareerDto dto, String email) {
        Sim sim = simRepo.findById(simId)
                .orElseThrow(() -> new NoSuchElementException("Sim does not exist"));

        Run run = sim.getRun();
        if (!run.getUser().getEmail().equals(email)) {
            throw new NoSuchElementException("User does not have a run with this id");
        }

        if (sim.getLifeStage().ordinal() < LifeStage.YOUNG_ADULT.ordinal()) {
            throw new IllegalStateException("Sim is too young");
        }

        if (!sim.getCareers().isEmpty()) {
            throw new IllegalStateException("Sim already has a career");
        }

        CareerBranch branch = careerBranchRepo.findById(dto.getCareerBranchId())
                .orElseThrow(() -> new NoSuchElementException("Career branch does not exist"));

        SimCareer career = new SimCareer();
        career.setCareerBranch(branch);
        career.setLevel(1);
        career.setUpdatedAt(LocalDateTime.now());
        career.setSim(sim);

        return mapperService.simCareerToDto(simCareerRepo.save(career));
    }

    @Override
    public SimCareerDto updateCareerLevel(int simId, int simCareerId, String email, UpdateSimCareerDto dto) {
        Sim sim = simRepo.findById(simId)
                .orElseThrow(() -> new NoSuchElementException("Sim does not exist"));

        if (!sim.getRun().getUser().getEmail().equals(email))
            throw new NoSuchElementException("User does not have a run with this id");

        SimCareer career = simCareerRepo.findById(simCareerId)
                .orElseThrow(() -> new NoSuchElementException("Career does not exist"));

        if (career.getSim().getSimId() != simId)
            throw new IllegalArgumentException("Career does not belong to this sim");

        CareerType careerType = career.getCareerBranch().getCareer().getCareerType();
        if (!careerType.isHasLevels())
            throw new IllegalArgumentException("This career cannot be updated");

        if (dto.getLevel() > careerType.getMaxLevel())
            throw new IllegalArgumentException("Level must be from 1 to " + careerType.getMaxLevel());

        career.setLevel(dto.getLevel());
        career.setUpdatedAt(LocalDateTime.now());

        return mapperService.simCareerToDto(simCareerRepo.save(career));
    }
}
