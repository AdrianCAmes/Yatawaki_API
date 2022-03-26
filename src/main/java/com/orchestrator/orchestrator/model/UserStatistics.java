package com.orchestrator.orchestrator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "[UserStatistics]", schema = "[Orchestrator]")
@Data
public class UserStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUserStatistics;
    private Integer triviasPlayed;
    private Integer triviasWon;
    private Integer concertsOrchestrated;
    private Double orchestrationAccuracy;
    private Integer status;
}
