package com.orchestrator.orchestrator.model.dto.composer.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;

public class ComposerChangeRequestDtoTest extends DtoTest<ComposerChangeRequestDto> {
    @Override
    protected ComposerChangeRequestDto getInstance() {
        return new ComposerChangeRequestDto();
    }
}