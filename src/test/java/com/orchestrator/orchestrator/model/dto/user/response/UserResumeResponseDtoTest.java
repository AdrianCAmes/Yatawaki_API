package com.orchestrator.orchestrator.model.dto.user.response;

import com.orchestrator.orchestrator.model.dto.DtoTest;
import com.orchestrator.orchestrator.model.dto.EqualsTester;
import org.junit.jupiter.api.Test;

public class UserResumeResponseDtoTest extends DtoTest<UserResumeResponseDto> {
    @Override
    protected UserResumeResponseDto getInstance() {
        return new UserResumeResponseDto();
    }

    @Test
    public void testEqualsAndHashCode() {
        UserResumeResponseDto userResumeResponseDtoTest = new UserResumeResponseDto();
        userResumeResponseDtoTest.setIdUser(1L);

        UserResumeResponseDto userChangeRequestDto1 = new UserResumeResponseDto();
        userChangeRequestDto1.setIdUser(2L);
        EqualsTester<UserResumeResponseDto> equalsTester = EqualsTester.newInstance( new UserResumeResponseDto() );
        equalsTester.assertEqual( new UserResumeResponseDto(), new UserResumeResponseDto() );
        equalsTester.assertNotEqual( userResumeResponseDtoTest, userChangeRequestDto1);
    }
}
