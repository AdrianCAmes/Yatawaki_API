package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum UserStatisticsStatusConstants {
    DELETED(0),
    ACTIVE(1);

    Integer value;

    UserStatisticsStatusConstants(Integer value) {
        this.value = value;
    }
}
