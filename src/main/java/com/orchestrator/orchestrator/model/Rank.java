package com.orchestrator.orchestrator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "[Rank]", schema = "[Orchestrator]")
@Data
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRank;
    private String name;
    private Integer maxExperience;
    private Integer status;
}
