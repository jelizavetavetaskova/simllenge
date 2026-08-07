package dev.vulpden.simllenge.sim.dto;

import dev.vulpden.simllenge.familyRole.dto.FamilyRoleDto;
import dev.vulpden.simllenge.sim.model.enums.LifeStage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimDto {
    private int simId;
    private String name;
    private LifeStage lifeStage;
    private FamilyRoleDto familyRole;
    private boolean alive;
}
