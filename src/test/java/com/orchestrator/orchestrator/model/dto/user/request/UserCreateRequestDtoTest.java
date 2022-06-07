package com.orchestrator.orchestrator.model.dto.user.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;

public class UserCreateRequestDtoTest extends DtoTest<UserCreateRequestDto> {
    @Override
    protected UserCreateRequestDto getInstance() {
        return new UserCreateRequestDto();
    }
}
