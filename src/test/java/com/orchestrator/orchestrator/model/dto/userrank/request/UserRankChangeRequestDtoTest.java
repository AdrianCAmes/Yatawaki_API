package com.orchestrator.orchestrator.model.dto.userrank.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;
import com.orchestrator.orchestrator.model.dto.EqualsTester;
import org.junit.jupiter.api.Test;

public class UserRankChangeRequestDtoTest extends DtoTest<UserRankChangeRequestDto> {
    @Override
    protected UserRankChangeRequestDto getInstance() {
        return new UserRankChangeRequestDto();
    }

    @Test
    public void testEqualsAndHashCode() {
        UserRankChangeRequestDto userRankChangeRequestDtoTest = new UserRankChangeRequestDto();
        userRankChangeRequestDtoTest.setIdRank(1L);

        UserRankChangeRequestDto userChangeRequestDto1 = new UserRankChangeRequestDto();
        userChangeRequestDto1.setIdRank(2L);
        EqualsTester<UserRankChangeRequestDto> equalsTester = EqualsTester.newInstance( new UserRankChangeRequestDto() );
        equalsTester.assertEqual( new UserRankChangeRequestDto(), new UserRankChangeRequestDto() );
        equalsTester.assertNotEqual( userRankChangeRequestDtoTest, userChangeRequestDto1);
    }
}
