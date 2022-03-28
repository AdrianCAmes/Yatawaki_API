package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum UserStatusConstants {
    DELETED(0),
    ACTIVE(1),
    BLOCKED(2);

    Integer value;

    UserStatusConstants(Integer value) {
        this.value = value;
    }
}
