package com.orchestrator.orchestrator.model.dto.userrank.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;
import com.orchestrator.orchestrator.model.dto.EqualsTester;
import org.junit.jupiter.api.Test;

public class UserRankUpdateRequestDtoTest extends DtoTest<UserRankUpdateRequestDto> {
    @Override
    protected UserRankUpdateRequestDto getInstance() {
        return new UserRankUpdateRequestDto();
    }

    @Test
    public void testEqualsAndHashCode() {
        UserRankUpdateRequestDto userRankUpdateRequestDtoTest = new UserRankUpdateRequestDto();
        userRankUpdateRequestDtoTest.setIdUser(1L);

        UserRankUpdateRequestDto userChangeRequestDto1 = new UserRankUpdateRequestDto();
        userChangeRequestDto1.setIdUser(2L);
        EqualsTester<UserRankUpdateRequestDto> equalsTester = EqualsTester.newInstance( new UserRankUpdateRequestDto() );
        equalsTester.assertEqual( new UserRankUpdateRequestDto(), new UserRankUpdateRequestDto() );
        equalsTester.assertNotEqual( userRankUpdateRequestDtoTest, userChangeRequestDto1);
    }
}