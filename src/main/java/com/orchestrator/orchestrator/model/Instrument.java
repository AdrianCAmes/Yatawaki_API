package com.orchestrator.orchestrator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "[Instrument]", schema = "[Orchestrator]")
@Data
public class Instrument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInstrument;
    private String name;
    private String longDescription;
    private String shortDescription;
    private String type;
    private Integer status;
    private String icon;
}
