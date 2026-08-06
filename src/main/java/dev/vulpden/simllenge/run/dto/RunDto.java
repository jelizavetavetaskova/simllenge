package dev.vulpden.simllenge.run.dto;

import dev.vulpden.simllenge.stage.dto.StageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RunDto {
    private int runId;
    private int budget;
    private StageDto stage;
}
