package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum QuestionStatusConstants {
    DELETED(0),
    ACTIVE(1);

    Integer value;

    QuestionStatusConstants(Integer value) {
        this.value = value;
    }
}
