package com.orchestrator.orchestrator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "[Achievement]", schema = "[Orchestrator]")
@Data
public class Achievement extends Unlockable {
}
