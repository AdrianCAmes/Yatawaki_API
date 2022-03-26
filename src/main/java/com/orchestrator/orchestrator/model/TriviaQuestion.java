package com.orchestrator.orchestrator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "[Trivia_Question]", schema = "[Orchestrator]")
@Data
public class TriviaQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTriviaQuestion;
    @ManyToOne
    @JoinColumn(name = "idGame", nullable = false)
    private Trivia trivia;
    @ManyToOne
    @JoinColumn(name = "idQuestion", nullable = false)
    private Question question;
    private Integer status;
}
