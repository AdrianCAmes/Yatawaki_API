package com.orchestrator.orchestrator.model.dto.user.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;
import com.orchestrator.orchestrator.model.dto.EqualsTester;
import org.junit.jupiter.api.Test;

public class UserRegisterRequestDtoTest extends DtoTest<UserRegisterRequestDto> {
    @Override
    protected UserRegisterRequestDto getInstance() {
        return new UserRegisterRequestDto();
    }

    @Test
    public void testEqualsAndHashCode() {
        UserRegisterRequestDto userRegisterRequestDto = new UserRegisterRequestDto();
        userRegisterRequestDto.setNickname("Hola2");
        UserRegisterRequestDto userRegisterRequestDto1 = new UserRegisterRequestDto();
        userRegisterRequestDto1.setNickname("Hola");
        EqualsTester<UserRegisterRequestDto> equalsTester = EqualsTester.newInstance( new UserRegisterRequestDto() );
        equalsTester.assertEqual( new UserRegisterRequestDto(), new UserRegisterRequestDto() );
        equalsTester.assertNotEqual( userRegisterRequestDto, userRegisterRequestDto1);
        equalsTester.assertImplementsEqualsAndHashCode();
    }

}