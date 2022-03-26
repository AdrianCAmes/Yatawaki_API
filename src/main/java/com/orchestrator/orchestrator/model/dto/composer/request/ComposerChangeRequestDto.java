package com.orchestrator.orchestrator.model.dto.composer.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ComposerChangeRequestDto {
    private Long idComposer;
    private String name;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private Integer status;
}
