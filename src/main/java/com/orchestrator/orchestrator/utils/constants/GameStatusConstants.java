package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum GameStatusConstants {
    DELETED(0),
    WAITING(1),
    STARTED(2),
    CANCELED(3),
    FINISHED(4);

    Integer value;

    GameStatusConstants(Integer value) {
        this.value = value;
    }
}
