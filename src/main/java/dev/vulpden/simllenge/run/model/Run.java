package dev.vulpden.simllenge.run.model;

import dev.vulpden.simllenge.challenge.model.Challenge;
import dev.vulpden.simllenge.sim.model.Sim;
import dev.vulpden.simllenge.stage.model.Stage;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "run")
@Getter
@Setter
@NoArgsConstructor
public class Run {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "run_id")
    private int runId;

    @Column(name = "budget")
    private int budget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @OneToMany(mappedBy = "run")
    private Set<Sim> sims;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id")
    private Stage stage;
}
