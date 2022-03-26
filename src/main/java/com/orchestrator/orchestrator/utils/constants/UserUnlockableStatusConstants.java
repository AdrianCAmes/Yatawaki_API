package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum UserUnlockableStatusConstants {
    BLOCKED(0),
    ACTIVE(1);

    Integer value;

    UserUnlockableStatusConstants(Integer value) {
        this.value = value;
    }
}
