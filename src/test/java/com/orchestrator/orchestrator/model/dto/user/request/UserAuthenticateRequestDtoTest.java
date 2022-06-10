package com.orchestrator.orchestrator.model.dto.user.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;
import com.orchestrator.orchestrator.model.dto.EqualsTester;
import org.junit.jupiter.api.Test;

public class UserAuthenticateRequestDtoTest extends DtoTest<UserAuthenticateRequestDto> {
    @Override
    protected UserAuthenticateRequestDto getInstance() {
        return new UserAuthenticateRequestDto();
    }

    @Test
    public void testEqualsAndHashCode() {
        UserAuthenticateRequestDto userAuthenticateRequestDtoTest = new UserAuthenticateRequestDto();
        userAuthenticateRequestDtoTest.setUniqueIdentifier("hola");

        UserAuthenticateRequestDto userChangeRequestDto1 = new UserAuthenticateRequestDto();
        userChangeRequestDto1.setUniqueIdentifier("dos");
        EqualsTester<UserAuthenticateRequestDto> equalsTester = EqualsTester.newInstance( new UserAuthenticateRequestDto() );
        equalsTester.assertEqual( new UserAuthenticateRequestDto(), new UserAuthenticateRequestDto() );
        equalsTester.assertNotEqual( userAuthenticateRequestDtoTest, userChangeRequestDto1);
    }
}