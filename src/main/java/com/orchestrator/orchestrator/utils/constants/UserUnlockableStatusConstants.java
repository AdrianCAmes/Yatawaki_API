package com.orchestrator.orchestrator.utils.constants;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserUnlockableStatusConstants {
    DELETED("Eliminado", 0),
    ACTIVE("Activo", 1),
    IN_USE("En uso", 2);

    String description;
    Integer value;

    UserUnlockableStatusConstants(String description, Integer value) {
        this.description = description;
        this.value = value;
    }
}
