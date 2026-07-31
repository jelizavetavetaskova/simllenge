package dev.vulpden.simllenge.requirement.model;

import dev.vulpden.simllenge.requirement.model.enums.MetricType;
import dev.vulpden.simllenge.requirement.model.enums.Scope;
import dev.vulpden.simllenge.stage.model.Stage;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "requirement")
@Getter
@Setter
@NoArgsConstructor
public class Requirement {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "requirement_id")
    private int requirementId;

    @Column(name = "description")
    private String description;

    @Column(name = "measurable")
    private boolean measurable = false;

    @Column(name = "scope")
    @Enumerated(EnumType.STRING)
    private Scope scope;

    @Column(name = "metric_type")
    @Enumerated(EnumType.STRING)
    private MetricType metricType;

    @Column(name = "target")
    private String target;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id")
    private Stage stage;
}
