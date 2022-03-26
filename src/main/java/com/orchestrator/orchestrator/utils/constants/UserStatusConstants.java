package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum UserStatusConstants {
    BLOCKED(0),
    ACTIVE(1);

    Integer value;

    UserStatusConstants(Integer value) {
        this.value = value;
    }
}
