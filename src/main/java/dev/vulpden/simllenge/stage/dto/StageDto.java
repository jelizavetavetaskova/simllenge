package dev.vulpden.simllenge.stage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StageDto {
    private int stageId;
    private String name;
    private int stageOrder;
    private boolean taxApplies;
}
