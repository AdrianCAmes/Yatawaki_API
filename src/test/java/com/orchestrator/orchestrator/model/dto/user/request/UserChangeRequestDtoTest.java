package com.orchestrator.orchestrator.model.dto.user.request;

import com.orchestrator.orchestrator.model.User;
import com.orchestrator.orchestrator.model.dto.DtoTest;
import com.orchestrator.orchestrator.model.dto.EqualsTester;
import org.junit.jupiter.api.Test;

public class UserChangeRequestDtoTest extends DtoTest<UserChangeRequestDto> {
    @Override
    protected UserChangeRequestDto getInstance() {
        return new UserChangeRequestDto();
    }

    @Test
    public void testEqualsAndHashCode() {
        UserChangeRequestDto userChangeRequestDto = new UserChangeRequestDto();
        userChangeRequestDto.setIdUser(1L);
        userChangeRequestDto.setNickname("Hola");
        UserChangeRequestDto userChangeRequestDto1 = new UserChangeRequestDto();
        userChangeRequestDto1.setIdUser(2L);
        userChangeRequestDto1.setNickname("Hola");
        EqualsTester<UserChangeRequestDto> equalsTester = EqualsTester.newInstance( new UserChangeRequestDto() );
        equalsTester.assertEqual( new UserChangeRequestDto(), new UserChangeRequestDto() );
        equalsTester.assertNotEqual( userChangeRequestDto, userChangeRequestDto1);
    }

}