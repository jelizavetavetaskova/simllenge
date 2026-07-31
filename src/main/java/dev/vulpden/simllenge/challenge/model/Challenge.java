package dev.vulpden.simllenge.challenge.model;

import dev.vulpden.simllenge.run.model.Run;
import dev.vulpden.simllenge.stage.model.Stage;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "challenge")
@Getter
@Setter
@NoArgsConstructor
public class Challenge {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_id")
    private int challengeId;

    @Column(name = "title", unique = true)
    private String title;

    @OneToMany(mappedBy = "challenge")
    private Set<Stage> stages;

    @OneToMany(mappedBy = "challenge")
    private Set<Run> runs;
}
