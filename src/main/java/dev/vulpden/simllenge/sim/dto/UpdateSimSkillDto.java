package dev.vulpden.simllenge.sim.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateSimSkillDto {
    @Min(value = 1, message = "Level must be greater or equal to 1")
    private int level;
}
