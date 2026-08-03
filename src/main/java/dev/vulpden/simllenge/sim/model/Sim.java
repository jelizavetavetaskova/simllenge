package dev.vulpden.simllenge.sim.model;

import dev.vulpden.simllenge.familyRole.model.FamilyRole;
import dev.vulpden.simllenge.run.model.Run;
import dev.vulpden.simllenge.sim.model.enums.LifeStage;
import dev.vulpden.simllenge.stage.model.Stage;
import dev.vulpden.simllenge.trait.model.Trait;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "sim")
@Getter
@Setter
@NoArgsConstructor
public class Sim {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sim_id")
    private int simId;

    @Column(name = "name")
    private String name;

    @Column(name = "life_stage")
    @Enumerated(EnumType.STRING)
    private LifeStage lifeStage;

    @Column(name = "alive")
    private boolean alive = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "run_id")
    private Run run;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_role_id")
    private FamilyRole familyRole;

    @OneToMany(mappedBy = "sim")
    private Set<SimSkill> skills;

    @OneToMany(mappedBy = "sim")
    private Set<SimCareer> careers;

    @ManyToMany
    @JoinTable(
            name = "sim_trait",
            joinColumns = @JoinColumn(name = "sim_id"),
            inverseJoinColumns = @JoinColumn(name = "trait_id")
    )
    private Set<Trait> traits;
}
