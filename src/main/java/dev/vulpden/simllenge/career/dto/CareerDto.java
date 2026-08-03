package dev.vulpden.simllenge.career.dto;

import dev.vulpden.simllenge.career.model.enums.CareerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CareerDto {
    private int careerId;
    private String name;
    private CareerType careerType;
    private List<CareerBranchDto> careerBranches;
}
