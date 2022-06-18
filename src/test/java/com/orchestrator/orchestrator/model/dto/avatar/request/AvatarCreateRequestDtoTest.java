package com.orchestrator.orchestrator.model.dto.avatar.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;

public class AvatarCreateRequestDtoTest extends DtoTest<AvatarCreateRequestDto> {
    @Override
    protected AvatarCreateRequestDto getInstance() {
        return new AvatarCreateRequestDto();
    }
}