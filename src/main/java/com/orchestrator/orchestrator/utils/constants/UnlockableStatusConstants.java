package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum UnlockableStatusConstants {
    DELETED(0),
    ACTIVE(1);

    Integer value;

    UnlockableStatusConstants(Integer value) {
        this.value = value;
    }
}
