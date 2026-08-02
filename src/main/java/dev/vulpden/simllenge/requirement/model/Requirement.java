package dev.vulpden.simllenge.requirement.model;

import dev.vulpden.simllenge.career.model.CareerBranch;
import dev.vulpden.simllenge.familyRole.model.FamilyRole;
import dev.vulpden.simllenge.requirement.model.enums.MetricType;
import dev.vulpden.simllenge.requirement.model.enums.Scope;
import dev.vulpden.simllenge.sim.model.enums.LifeStage;
import dev.vulpden.simllenge.skill.model.Skill;
import dev.vulpden.simllenge.stage.model.Stage;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "requirement")
@Getter
@Setter
@NoArgsConstructor
public class Requirement {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "requirement_id")
    private int requirementId;

    @Column(name = "description")
    private String description;

    @Column(name = "scope")
    @Enumerated(EnumType.STRING)
    private Scope scope;

    @Column(name = "metric_type")
    @Enumerated(EnumType.STRING)
    private MetricType metricType;

    @Column(name = "target")
    private String target;

    @Column(name = "min_life_stage")
    @Enumerated(EnumType.STRING)
    private LifeStage minLifeStage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @ManyToMany
    @JoinTable(
            name = "requirement_skill",
            joinColumns = @JoinColumn(name = "requirement_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "career_branch_id")
    private CareerBranch careerBranch;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private FamilyRole role;
}
