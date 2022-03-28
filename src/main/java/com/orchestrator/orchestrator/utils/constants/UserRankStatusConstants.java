package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum UserRankStatusConstants {
    DELETED(0),
    ACTIVE(1),
    FINISHED(2);

    Integer value;

    UserRankStatusConstants(Integer value) {
        this.value = value;
    }
}
