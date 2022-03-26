package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum TriviaUserStatusConstants {
    BLOCKED(0),
    ACTIVE(1);

    Integer value;

    TriviaUserStatusConstants(Integer value) {
        this.value = value;
    }
}
