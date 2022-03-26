package com.orchestrator.orchestrator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "[Symphony]", schema = "[Orchestrator]")
@Data
public class Symphony extends Unlockable {
    @ManyToOne
    @JoinColumn(name = "idComposer", nullable = false)
    private Composer composer;
    private Integer year;
    private Integer duration;
    private String type;
}
