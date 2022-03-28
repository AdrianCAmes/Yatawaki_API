package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum RankStatusConstants {
    DELETED(0),
    ACTIVE(1);

    Integer value;

    RankStatusConstants(Integer value) {
        this.value = value;
    }
}
