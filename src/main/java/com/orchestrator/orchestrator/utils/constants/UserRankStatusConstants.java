package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum UserRankStatusConstants {
    BLOCKED(0),
    ACTIVE(1);

    Integer value;

    UserRankStatusConstants(Integer value) {
        this.value = value;
    }
}
