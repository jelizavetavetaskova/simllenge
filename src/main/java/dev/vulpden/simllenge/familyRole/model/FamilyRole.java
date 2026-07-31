package dev.vulpden.simllenge.familyRole.model;

import dev.vulpden.simllenge.sim.model.Sim;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "family_role")
@Getter
@Setter
@NoArgsConstructor
public class FamilyRole {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "family_role_id")
    private int familyRoleId;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "familyRole")
    private Set<Sim> sims;
}
