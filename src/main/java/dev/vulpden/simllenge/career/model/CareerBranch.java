package dev.vulpden.simllenge.career.model;

import dev.vulpden.simllenge.requirement.model.Requirement;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "career_branch")
@Getter
@Setter
@NoArgsConstructor
public class CareerBranch {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "career_branch_id")
    private int careerBranchId;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "career_id")
    private Career career;

    @OneToMany(mappedBy = "careerBranch")
    private Set<Requirement> requirements;
}
