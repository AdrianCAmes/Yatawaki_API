package com.orchestrator.orchestrator.utils.constants;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SymphonyGestureStatusConstants {
    DELETED("Eliminado", 0),
    ACTIVE("Activo", 1);

    String description;
    Integer value;

    SymphonyGestureStatusConstants(String description, Integer value) {
        this.description = description;
        this.value = value;
    }
}
