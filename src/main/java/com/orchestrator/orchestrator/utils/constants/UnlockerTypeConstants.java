package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum UnlockerTypeConstants {
    RANK("Rank");

    String name;

    UnlockerTypeConstants(String name) {
        this.name = name;
    }
}
