package com.orchestrator.orchestrator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "[Symphony_Instrument]", schema = "[Orchestrator]")
@Data
public class SymphonyInstrument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSymphonyInstrument;
    @ManyToOne
    @JoinColumn(name = "idUnlockable", nullable = false)
    private Symphony symphony;
    @ManyToOne
    @JoinColumn(name = "idInstrument", nullable = false)
    private Instrument instrument;
    private Integer status;
}
