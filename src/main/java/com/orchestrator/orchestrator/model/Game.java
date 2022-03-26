package com.orchestrator.orchestrator.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "[Game]", schema = "[Orchestrator]")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGame;
    @ManyToOne
    @JoinColumn(name = "idUnlockable", nullable = false)
    private Symphony symphony;
    private LocalDate playedDate;
    private Integer status;
}
