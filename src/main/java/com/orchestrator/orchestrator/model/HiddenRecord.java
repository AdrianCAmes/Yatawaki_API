package com.orchestrator.orchestrator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "[HiddenRecord]", schema = "[Orchestrator]")
@Data
public class HiddenRecord extends Unlockable {
    private String recordContent;
}
