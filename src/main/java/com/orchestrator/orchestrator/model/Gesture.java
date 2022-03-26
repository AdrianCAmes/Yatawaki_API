package com.orchestrator.orchestrator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "[Gesture]", schema = "[Orchestrator]")
@Data
public class Gesture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGesture;
    private String name;
    private String description;
    private Integer status;
}
