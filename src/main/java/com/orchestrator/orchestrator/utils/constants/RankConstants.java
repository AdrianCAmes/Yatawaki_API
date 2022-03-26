package com.orchestrator.orchestrator.utils.constants;

import lombok.Getter;

@Getter
public enum RankConstants {
    RANK_ONE("One"),
    RANK_TWO("Two"),
    RANK_THREE("Three"),
    RANK_FOUR("Four"),
    RANK_FIVE("Five"),
    RANK_SIX("Six");

    String name;

    RankConstants(String name) {
        this.name = name;
    }
}
