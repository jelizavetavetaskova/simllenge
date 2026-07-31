package dev.vulpden.simllenge.sim.model;

import dev.vulpden.simllenge.career.model.CareerBranch;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "sim_career")
@Getter
@Setter
@NoArgsConstructor
public class SimCareer {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sim_career_id")
    private int simCareerId;

    @Column(name = "level")
    private int level;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sim_id")
    private Sim sim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "career_branch_id")
    private CareerBranch careerBranch;
}
