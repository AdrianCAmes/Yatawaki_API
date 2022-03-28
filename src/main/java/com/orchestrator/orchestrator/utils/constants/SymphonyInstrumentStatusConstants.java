package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum SymphonyInstrumentStatusConstants {
    DELETED(0),
    ACTIVE(1);

    Integer value;

    SymphonyInstrumentStatusConstants(Integer value) {
        this.value = value;
    }
}
