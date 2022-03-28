package com.orchestrator.orchestrator.utils.constants;

public enum UserRoleConstants {
    ADMIN(0),
    PLAYER(1);

    Integer value;

    UserRoleConstants(Integer value) {
        this.value = value;
    }
}
