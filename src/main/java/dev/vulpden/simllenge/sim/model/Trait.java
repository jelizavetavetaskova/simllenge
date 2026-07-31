package dev.vulpden.simllenge.sim.model;

import dev.vulpden.simllenge.career.model.Career;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "trait")
@Getter
@Setter
@NoArgsConstructor
public class Trait {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trait_id")
    private int traitId;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "traits")
    private Set<Sim> sims;

    @ManyToMany(mappedBy = "traits")
    private Set<Career> careers;
}
