package dev.vulpden.simllenge.sim.dto;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddSimCareerDto {
    @Positive(message = "Career branch id must be positive")
    private int careerBranchId;
}
