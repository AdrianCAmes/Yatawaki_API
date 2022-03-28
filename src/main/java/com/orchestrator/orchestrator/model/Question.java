package com.orchestrator.orchestrator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "[Question]", schema = "[Orchestrator]")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQuestion;
    @ManyToOne
    @JoinColumn(name = "idRank", nullable = false)
    private Rank rank;
    private String description;
    private String optionsJson;
    private Integer status;
}
