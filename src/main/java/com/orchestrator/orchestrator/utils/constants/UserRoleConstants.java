package com.orchestrator.orchestrator.utils.constants;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserRoleConstants {
    ADMIN("Administrador", "ADMIN"),
    PLAYER("Jugador", "PLAYER");

    String description;
    String value;

    UserRoleConstants(String description, String value) {
        this.description = description;
        this.value = value;
    }
}
