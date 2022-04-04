package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum UnlockerTypeConstants {
    RANK("Rank"),
    POINTS("Points"),
    CONCERTS("Concerts"),
    ACCURACY("Accuracy");

    String name;

    UnlockerTypeConstants(String name) {
        this.name = name;
    }
}
