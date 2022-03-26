package com.orchestrator.orchestrator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "[Trivia_User]", schema = "[Orchestrator]")
@Data
public class TriviaUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTriviaUser;
    @ManyToOne
    @JoinColumn(name = "idGame", nullable = false)
    private Trivia trivia;
    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;
    private Boolean isWinner;
    private String answeredQuestionsJson;
    private Integer points;
    private Double accuracyRate;
    private Integer status;
}
