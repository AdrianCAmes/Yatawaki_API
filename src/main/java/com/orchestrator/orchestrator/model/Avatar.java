package com.orchestrator.orchestrator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "[Avatar]", schema = "[Orchestrator]")
@Data
public class Avatar extends Unlockable {
    private String enhancedFeaturesJson;
}
