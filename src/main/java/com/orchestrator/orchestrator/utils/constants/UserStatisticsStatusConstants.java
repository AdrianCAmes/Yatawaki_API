package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum UserStatisticsStatusConstants {
    BLOCKED(0),
    ACTIVE(1);

    Integer value;

    UserStatisticsStatusConstants(Integer value) {
        this.value = value;
    }
}
