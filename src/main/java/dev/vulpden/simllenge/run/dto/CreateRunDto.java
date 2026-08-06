package dev.vulpden.simllenge.run.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRunDto {
    @PositiveOrZero(message = "Budget must be greater or equal to 0")
    @NotNull(message = "Budget is required")
    private Integer budget;

    @Positive(message = "Stage id must be greater than 0")
    @NotNull(message = "Stage is required")
    private Integer stageId;
}
