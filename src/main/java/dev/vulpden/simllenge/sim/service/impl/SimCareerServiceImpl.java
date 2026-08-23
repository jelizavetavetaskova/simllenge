package dev.vulpden.simllenge.sim.service.impl;

import dev.vulpden.simllenge.career.dto.CareerDto;
import dev.vulpden.simllenge.career.model.Career;
import dev.vulpden.simllenge.general.service.MapperService;
import dev.vulpden.simllenge.requirement.model.Requirement;
import dev.vulpden.simllenge.requirement.model.enums.MetricType;
import dev.vulpden.simllenge.run.model.Run;
import dev.vulpden.simllenge.sim.model.Sim;
import dev.vulpden.simllenge.sim.model.enums.LifeStage;
import dev.vulpden.simllenge.sim.repo.SimRepo;
import dev.vulpden.simllenge.sim.service.SimCareerService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SimCareerServiceImpl implements SimCareerService {
    private final SimRepo simRepo;
    private final MapperService mapperService;

    public SimCareerServiceImpl(SimRepo simRepo, MapperService mapperService) {
        this.simRepo = simRepo;
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
}
