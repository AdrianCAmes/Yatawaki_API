package com.orchestrator.orchestrator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "[Trivia]", schema = "[Orchestrator]")
@Data
public class Trivia extends Game {
    private String hallId;
    private Integer numberOfParticipants;
    private Integer currencyPool;
}
