package dev.vulpden.simllenge.familyRole.controller;

import dev.vulpden.simllenge.familyRole.dto.FamilyRoleDto;
import dev.vulpden.simllenge.familyRole.service.FamilyRoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/family-roles")
public class FamilyRoleController {
    private final FamilyRoleService familyRoleService;

    public FamilyRoleController(FamilyRoleService familyRoleService) {
        this.familyRoleService = familyRoleService;
    }

    @GetMapping
    public List<FamilyRoleDto> getFamilyRoles() {
        return familyRoleService.getAllFamilyRoles();
    }
}
