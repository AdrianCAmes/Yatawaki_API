package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum GestureStatusConstants {
    BLOCKED(0),
    ACTIVE(1);

    Integer value;

    GestureStatusConstants(Integer value) {
        this.value = value;
    }
}
