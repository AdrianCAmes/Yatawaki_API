package com.orchestrator.orchestrator.model.dto.user.response;

import com.orchestrator.orchestrator.model.dto.DtoTest;

public class UserAuthenticateResponseDtoTest extends DtoTest<UserAuthenticateResponseDto> {
    @Override
    protected UserAuthenticateResponseDto getInstance() {
        return new UserAuthenticateResponseDto();
    }
}
