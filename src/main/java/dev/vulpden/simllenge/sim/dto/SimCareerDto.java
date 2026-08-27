package dev.vulpden.simllenge.sim.dto;

import dev.vulpden.simllenge.career.dto.CareerBranchDto;
import dev.vulpden.simllenge.career.model.enums.CareerType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SimCareerDto {
    private int simCareerId;
    private CareerBranchDto careerBranch;
    private String careerName;
    private CareerType careerType;
    private int level;
    private LocalDateTime updatedAt;
}
