package com.orchestrator.orchestrator.model.dto.user.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;

public class UserUpdateRequestDtoTest extends DtoTest<UserUpdateRequestDto> {
    @Override
    protected UserUpdateRequestDto getInstance() {
        return new UserUpdateRequestDto();
    }
}