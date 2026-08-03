package dev.vulpden.simllenge.career.service.impl;

import dev.vulpden.simllenge.career.dto.CareerDto;
import dev.vulpden.simllenge.career.repo.CareerRepo;
import dev.vulpden.simllenge.career.service.CareerService;
import dev.vulpden.simllenge.general.service.MapperService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareerServiceImpl implements CareerService {
    private final CareerRepo careerRepo;
    private final MapperService mapperService;

    public CareerServiceImpl(CareerRepo careerRepo, MapperService mapperService) {
        this.careerRepo = careerRepo;
        this.mapperService = mapperService;
    }

    @Override
    public List<CareerDto> getAllCareers() {
        return careerRepo.findAllCareers()
                .stream()
                .map(mapperService::careerToDto)
                .toList();
    }
}
