package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum TriviaUserStatusConstants {
    DELETED(0),
    ACTIVE(1),
    CANCELED(2);

    Integer value;

    TriviaUserStatusConstants(Integer value) {
        this.value = value;
    }
}
