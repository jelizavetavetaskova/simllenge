package dev.vulpden.simllenge.career.model;

import dev.vulpden.simllenge.career.model.enums.CareerType;
import dev.vulpden.simllenge.trait.model.Trait;
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

    @Column(name = "career_type")
    @Enumerated(EnumType.STRING)
    private CareerType careerType;

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
