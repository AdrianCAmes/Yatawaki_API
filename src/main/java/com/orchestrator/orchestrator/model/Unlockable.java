package com.orchestrator.orchestrator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "[Unlockable]", schema = "[Orchestrator]")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Unlockable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUnlockable;
    private String name;
    private String description;
    private String rareness;
    private String unlockerType;
    private Integer unlockerValue;
    private Integer coinsCost;
    private Integer notesCost;
    private byte[] icon;
    private Integer status;
}
