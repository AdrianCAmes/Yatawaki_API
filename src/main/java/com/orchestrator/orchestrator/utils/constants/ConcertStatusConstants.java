package com.orchestrator.orchestrator.utils.constants;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ConcertStatusConstants {
    DELETED("Eliminado", 0),
    STARTED("Iniciado", 1),
    CANCELED("Cancelado", 2),
    FINISHED("Terminado", 3);

    String description;
    Integer value;

    ConcertStatusConstants(String description, Integer value) {
        this.description = description;
        this.value = value;
    }
}
