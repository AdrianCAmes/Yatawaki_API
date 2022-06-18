package com.orchestrator.orchestrator.model.dto.user.response;

import com.orchestrator.orchestrator.model.dto.DtoTest;
import com.orchestrator.orchestrator.model.dto.EqualsTester;
import org.junit.jupiter.api.Test;

public class UserProfileResponseDtoTest extends DtoTest<UserProfileResponseDto> {
    @Override
    protected UserProfileResponseDto getInstance() {
        return new UserProfileResponseDto();
    }

    @Test
    public void testEqualsAndHashCode() {
        UserProfileResponseDto userProfileResponseDtoTest = new UserProfileResponseDto();
        userProfileResponseDtoTest.setNickname("hola");

        UserProfileResponseDto userChangeRequestDto1 = new UserProfileResponseDto();
        userChangeRequestDto1.setNickname("dos");
        EqualsTester<UserProfileResponseDto> equalsTester = EqualsTester.newInstance( new UserProfileResponseDto() );
        equalsTester.assertEqual( new UserProfileResponseDto(), new UserProfileResponseDto() );
        equalsTester.assertNotEqual( userProfileResponseDtoTest, userChangeRequestDto1);
    }
}
