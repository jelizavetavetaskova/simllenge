package dev.vulpden.simllenge.sim.dto;

import dev.vulpden.simllenge.sim.model.enums.LifeStage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSimDto {
    @NotBlank(message = "Sim name is required")
    private String name;

    @NotNull(message = "Sim life stage is required")
    private LifeStage lifeStage;

    @NotNull(message = "Family role is required")
    @Positive(message = "Family role id must be greater than 0")
    private Integer familyRoleId;
}
