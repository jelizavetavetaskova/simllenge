package dev.vulpden.simllenge.skill.model;

import dev.vulpden.simllenge.sim.model.SimSkill;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "skill")
@Getter
@Setter
@NoArgsConstructor
public class Skill {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private int skillId;

    @Column(name = "name")
    private String name;

    @Column(name = "level_cap")
    private int levelCap = 10;

    @OneToMany(mappedBy = "skill")
    Set<SimSkill> skills;
}
