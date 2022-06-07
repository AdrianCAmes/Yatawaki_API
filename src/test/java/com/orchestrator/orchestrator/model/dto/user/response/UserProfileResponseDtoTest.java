package com.orchestrator.orchestrator.model.dto.user.response;

import com.orchestrator.orchestrator.model.dto.DtoTest;

public class UserProfileResponseDtoTest extends DtoTest<UserProfileResponseDto> {
    @Override
    protected UserProfileResponseDto getInstance() {
        return new UserProfileResponseDto();
    }
}
