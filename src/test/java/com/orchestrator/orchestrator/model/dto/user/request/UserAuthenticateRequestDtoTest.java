package com.orchestrator.orchestrator.model.dto.user.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;

public class UserAuthenticateRequestDtoTest extends DtoTest<UserAuthenticateRequestDto> {
    @Override
    protected UserAuthenticateRequestDto getInstance() {
        return new UserAuthenticateRequestDto();
    }
}