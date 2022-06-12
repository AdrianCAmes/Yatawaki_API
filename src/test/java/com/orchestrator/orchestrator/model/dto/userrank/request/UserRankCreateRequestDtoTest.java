package com.orchestrator.orchestrator.model.dto.userrank.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;
import com.orchestrator.orchestrator.model.dto.EqualsTester;
import org.junit.jupiter.api.Test;

public class UserRankCreateRequestDtoTest extends DtoTest<UserRankCreateRequestDto> {
    @Override
    protected UserRankCreateRequestDto getInstance() {
        return new UserRankCreateRequestDto();
    }

    @Test
    public void testEqualsAndHashCode() {
        UserRankCreateRequestDto userRankCreateRequestDtoTest = new UserRankCreateRequestDto();
        userRankCreateRequestDtoTest.setIdUser(1L);

        UserRankCreateRequestDto userChangeRequestDto1 = new UserRankCreateRequestDto();
        userChangeRequestDto1.setIdUser(2L);
        EqualsTester<UserRankCreateRequestDto> equalsTester = EqualsTester.newInstance( new UserRankCreateRequestDto() );
        equalsTester.assertEqual( new UserRankCreateRequestDto(), new UserRankCreateRequestDto() );
        equalsTester.assertNotEqual( userRankCreateRequestDtoTest, userChangeRequestDto1);
    }
}