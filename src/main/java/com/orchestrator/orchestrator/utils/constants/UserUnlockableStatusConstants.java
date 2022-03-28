package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum UserUnlockableStatusConstants {
    DELETED(0),
    ACTIVE(1),
    IN_USE(2);

    Integer value;

    UserUnlockableStatusConstants(Integer value) {
        this.value = value;
    }
}
