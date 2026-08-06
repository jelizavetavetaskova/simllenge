package dev.vulpden.simllenge.stage.model;

import dev.vulpden.simllenge.challenge.model.Challenge;
import dev.vulpden.simllenge.requirement.model.Requirement;
import dev.vulpden.simllenge.run.model.Run;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "stage")
@Getter
@Setter
@NoArgsConstructor
public class Stage {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stage_id")
    private int stageId;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "stage_order")
    private int stageOrder;

    @Column(name = "tax_applies")
    private boolean taxApplies = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @OneToMany(mappedBy = "stage")
    private Set<Requirement> requirements;

    @OneToMany(mappedBy = "stage")
    private Set<Run> runs;
}
