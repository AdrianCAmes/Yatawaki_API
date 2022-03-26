package com.orchestrator.orchestrator.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "[Composer]", schema = "[Orchestrator]")
@Data
public class Composer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComposer;
    private String name;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private Integer status;
}
