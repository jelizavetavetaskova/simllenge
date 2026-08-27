package dev.vulpden.simllenge.career.model.enums;

import lombok.Getter;

@Getter
public enum CareerType {
    FULL_TIME(10, true),
    PART_TIME(5, true),
    FREELANCE(0, false);

    private final int maxLevel;
    private final boolean hasLevels;

    CareerType(int maxLevel, boolean hasLevels) {
        this.maxLevel = maxLevel;
        this.hasLevels = hasLevels;
    }
}
