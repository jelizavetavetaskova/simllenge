package dev.vulpden.simllenge.sim.model.enums;

public enum LifeStage {
    NEWBORN, INFANT, TODDLER, CHILD, TEEN, YOUNG_ADULT, ADULT, ELDER;

    private static final LifeStage[] STAGES = values();

    public LifeStage next() {
        if (this.ordinal() == STAGES.length - 1)
            throw new IllegalStateException("Elder sim cannot age up");
        return STAGES[this.ordinal() + 1];
    }
}
