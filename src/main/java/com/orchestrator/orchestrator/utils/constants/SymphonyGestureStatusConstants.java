package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum SymphonyGestureStatusConstants {
    BLOCKED(0),
    ACTIVE(1);

    Integer value;

    SymphonyGestureStatusConstants(Integer value) {
        this.value = value;
    }
}
