package com.orchestrator.orchestrator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "[Concert]", schema = "[Orchestrator]")
@Data
public class Concert extends Game {
    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;
    private Integer points;
    private Double accuracyRate;
    private Integer gesturesCompleted;
}
