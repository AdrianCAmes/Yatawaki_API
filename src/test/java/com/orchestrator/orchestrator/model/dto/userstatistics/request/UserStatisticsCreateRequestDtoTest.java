package com.orchestrator.orchestrator.model.dto.userstatistics.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;
import com.orchestrator.orchestrator.model.dto.EqualsTester;
import org.junit.jupiter.api.Test;

public class UserStatisticsCreateRequestDtoTest extends DtoTest<UserStatisticsCreateRequestDto> {
    @Override
    protected UserStatisticsCreateRequestDto getInstance() {
        return new UserStatisticsCreateRequestDto();
    }

    @Test
    public void testEqualsAndHashCode() {
        UserStatisticsCreateRequestDto userStatisticsCreateRequestDtoTest = new UserStatisticsCreateRequestDto();

        UserStatisticsCreateRequestDto userChangeRequestDto1 = new UserStatisticsCreateRequestDto();
        EqualsTester<UserStatisticsCreateRequestDto> equalsTester = EqualsTester.newInstance( new UserStatisticsCreateRequestDto() );
        equalsTester.assertEqual( new UserStatisticsCreateRequestDto(), new UserStatisticsCreateRequestDto() );
        equalsTester.assertEqual( userStatisticsCreateRequestDtoTest, userChangeRequestDto1);
    }
}