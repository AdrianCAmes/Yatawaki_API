package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum StringConstants {
    EMPTY("");

    String value;

    StringConstants(String value) {
        this.value = value;
    }
}
