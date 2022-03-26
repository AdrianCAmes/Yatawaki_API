package com.orchestrator.orchestrator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "[Symphony_Gesture]", schema = "[Orchestrator]")
@Data
public class SymphonyGesture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSymphonyGesture;
    @ManyToOne
    @JoinColumn(name = "idGesture", nullable = false)
    private Gesture gesture;
    @ManyToOne
    @JoinColumn(name = "idUnlockable", nullable = false)
    private Symphony symphony;
    private Integer beginningTime;
    private Integer endingTime;
    private Integer status;
}
