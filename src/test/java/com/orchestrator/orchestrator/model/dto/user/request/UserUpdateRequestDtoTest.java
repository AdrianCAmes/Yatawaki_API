package com.orchestrator.orchestrator.model.dto.user.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;
import com.orchestrator.orchestrator.model.dto.EqualsTester;
import org.junit.jupiter.api.Test;

public class UserUpdateRequestDtoTest extends DtoTest<UserUpdateRequestDto> {
    @Override
    protected UserUpdateRequestDto getInstance() {
        return new UserUpdateRequestDto();
    }

    @Test
    public void testEqualsAndHashCode() {
        UserUpdateRequestDto userUpdateRequestDto = new UserUpdateRequestDto();
        userUpdateRequestDto.setNickname("Hola2");
        UserUpdateRequestDto userUpdateRequestDto1 = new UserUpdateRequestDto();
        userUpdateRequestDto1.setNickname("Hola");
        EqualsTester<UserUpdateRequestDto> equalsTester = EqualsTester.newInstance( new UserUpdateRequestDto() );
        equalsTester.assertEqual( new UserUpdateRequestDto(), new UserUpdateRequestDto() );
        equalsTester.assertNotEqual( userUpdateRequestDto, userUpdateRequestDto1);
        equalsTester.assertImplementsEqualsAndHashCode();
    }

}