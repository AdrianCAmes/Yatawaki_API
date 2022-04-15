package com.orchestrator.orchestrator.utils.constants;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UnlockerTypeConstants {
    RANK("Rango", "Rank"),
    POINTS("Puntos", "Points"),
    CONCERTS("Conciertos", "Concerts"),
    ACCURACY("Precision", "Accuracy");

    String description;
    String value;

    UnlockerTypeConstants(String description, String value) {
        this.description = description;
        this.value = value;
    }
}
