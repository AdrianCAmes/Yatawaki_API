package com.orchestrator.orchestrator.model.dto.user.request;

import com.orchestrator.orchestrator.model.User;
import com.orchestrator.orchestrator.model.dto.DtoTest;
import com.orchestrator.orchestrator.model.dto.EqualsTester;
import org.junit.jupiter.api.Test;

public class UserCreateRequestDtoTest extends DtoTest<UserCreateRequestDto> {
    @Override
    protected UserCreateRequestDto getInstance() {
        return new UserCreateRequestDto();
    }

    @Test
    public void testEqualsAndHashCode() {
        UserCreateRequestDto userCreateRequestDto = new UserCreateRequestDto();
        userCreateRequestDto.setNickname("Hola2");
        UserCreateRequestDto userCreateRequestDto1 = new UserCreateRequestDto();
        userCreateRequestDto1.setNickname("Hola");
        EqualsTester<UserCreateRequestDto> equalsTester = EqualsTester.newInstance( new UserCreateRequestDto() );
        equalsTester.assertEqual( new UserCreateRequestDto(), new UserCreateRequestDto() );
        equalsTester.assertNotEqual( userCreateRequestDto, userCreateRequestDto1);
        equalsTester.assertImplementsEqualsAndHashCode();
    }

}