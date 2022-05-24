package com.orchestrator.orchestrator.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "[Concert]", schema = "[Orchestrator]")
@Data
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConcert;
    @ManyToOne
    @JoinColumn(name = "idUnlockable", nullable = false)
    private Symphony symphony;
    private LocalDate playedDate;
    private Integer status;
    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;
    private Integer points;
    private Double accuracyRate;
    private Integer gesturesCompleted;
}
