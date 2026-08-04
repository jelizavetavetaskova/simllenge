package dev.vulpden.simllenge.familyRole.service.impl;

import dev.vulpden.simllenge.familyRole.dto.FamilyRoleDto;
import dev.vulpden.simllenge.familyRole.repo.FamilyRoleRepo;
import dev.vulpden.simllenge.familyRole.service.FamilyRoleService;
import dev.vulpden.simllenge.general.service.MapperService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyRoleServiceImpl implements FamilyRoleService {
    private final FamilyRoleRepo familyRoleRepo;
    private final MapperService mapperService;

    public FamilyRoleServiceImpl(FamilyRoleRepo familyRoleRepo, MapperService mapperService) {
        this.familyRoleRepo = familyRoleRepo;
        this.mapperService = mapperService;
    }

    @Override
    public List<FamilyRoleDto> getAllFamilyRoles() {
        return familyRoleRepo.findAll()
                .stream()
                .map(mapperService::familyRoleToDto)
                .toList();
    }
}
