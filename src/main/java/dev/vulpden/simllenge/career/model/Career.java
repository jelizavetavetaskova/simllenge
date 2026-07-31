package dev.vulpden.simllenge.career.model;

import dev.vulpden.simllenge.sim.model.Trait;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "career")
@Getter
@Setter
@NoArgsConstructor
public class Career {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "career_id")
    private int careerId;

    @Column(name = "name")
    private String name;

    @Column(name = "level_cap")
    private int levelCap;

    @OneToMany(mappedBy = "career")
    private Set<CareerBranch> careerBranches;

    @ManyToMany
    @JoinTable(
            name = "career_trait",
            joinColumns = @JoinColumn(name = "career_id"),
            inverseJoinColumns = @JoinColumn(name = "trait_id")
    )
    private Set<Trait> traits;
}
