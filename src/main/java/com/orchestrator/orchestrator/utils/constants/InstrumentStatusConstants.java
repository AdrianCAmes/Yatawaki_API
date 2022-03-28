package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum InstrumentStatusConstants {
    DELETED(0),
    ACTIVE(1);

    Integer value;

    InstrumentStatusConstants(Integer value) {
        this.value = value;
    }
}
