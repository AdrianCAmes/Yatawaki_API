package com.orchestrator.orchestrator.model.dto.user.response;

import com.orchestrator.orchestrator.model.dto.DtoTest;
import com.orchestrator.orchestrator.model.dto.EqualsTester;
import org.junit.jupiter.api.Test;

public class UserAuthenticateResponseDtoTest extends DtoTest<UserAuthenticateResponseDto> {
    @Override
    protected UserAuthenticateResponseDto getInstance() {
        return new UserAuthenticateResponseDto();
    }

    @Test
    public void testEqualsAndHashCode() {
        UserAuthenticateResponseDto userAuthenticateResponseDtoTest = new UserAuthenticateResponseDto();
        userAuthenticateResponseDtoTest.setJwt("no");

        UserAuthenticateResponseDto userChangeRequestDto1 = new UserAuthenticateResponseDto();
        userChangeRequestDto1.setJwt("si");
        EqualsTester<UserAuthenticateResponseDto> equalsTester = EqualsTester.newInstance( new UserAuthenticateResponseDto() );
        equalsTester.assertEqual( new UserAuthenticateResponseDto(), new UserAuthenticateResponseDto() );
        equalsTester.assertNotEqual( userAuthenticateResponseDtoTest, userChangeRequestDto1);
    }
}