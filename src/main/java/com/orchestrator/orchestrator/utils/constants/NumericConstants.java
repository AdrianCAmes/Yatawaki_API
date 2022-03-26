package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum NumericConstants {
    ZERO(0),
    ONE(1);

    Integer value;

    NumericConstants(Integer value) {
        this.value = value;
    }
}
