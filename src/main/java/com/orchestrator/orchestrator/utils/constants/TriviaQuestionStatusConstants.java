package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum TriviaQuestionStatusConstants {
    BLOCKED(0),
    ACTIVE(1);

    Integer value;

    TriviaQuestionStatusConstants(Integer value) {
        this.value = value;
    }
}
