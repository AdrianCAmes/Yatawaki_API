package com.orchestrator.orchestrator.utils.constants;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UnlockableRarenessConstants {
    WOOD("Madera", "Wood"),
    BRONZE("Bronce", "Bronze"),
    SILVER("Plata", "Silver"),
    GOLD("Oro", "Gold"),
    PLATINUM("Platino", "Platinum");

    String description;
    String value;

    UnlockableRarenessConstants(String description, String value) {
        this.description = description;
        this.value = value;
    }
}
