package com.orchestrator.orchestrator.model.dto.user.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;

public class UserRegisterRequestDtoTest extends DtoTest<UserRegisterRequestDto> {
    @Override
    protected UserRegisterRequestDto getInstance() {
        return new UserRegisterRequestDto();
    }
}
