package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum TriviaQuestionStatusConstants {
    DELETED(0),
    PENDING(1),
    ASKED(2);

    Integer value;

    TriviaQuestionStatusConstants(Integer value) {
        this.value = value;
    }
}
