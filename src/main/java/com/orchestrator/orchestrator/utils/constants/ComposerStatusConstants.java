package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum ComposerStatusConstants {
    DELETED(0),
    ACTIVE(1);

    Integer value;

    ComposerStatusConstants(Integer value) {
        this.value = value;
    }
}
