package com.orchestrator.orchestrator.model.dto.userstatistics.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;
import com.orchestrator.orchestrator.model.dto.EqualsTester;
import org.junit.jupiter.api.Test;

public class UserStatisticsChangeRequestDtoTest extends DtoTest<UserStatisticsChangeRequestDto> {
    @Override
    protected UserStatisticsChangeRequestDto getInstance() {
        return new UserStatisticsChangeRequestDto();
    }

    @Test
    public void testEqualsAndHashCode() {
        UserStatisticsChangeRequestDto userStatisticsChangeRequestDtoTest = new UserStatisticsChangeRequestDto();
        userStatisticsChangeRequestDtoTest.setIdUserStatistics(1L);

        UserStatisticsChangeRequestDto userChangeRequestDto1 = new UserStatisticsChangeRequestDto();
        userChangeRequestDto1.setIdUserStatistics(2L);
        EqualsTester<UserStatisticsChangeRequestDto> equalsTester = EqualsTester.newInstance( new UserStatisticsChangeRequestDto() );
        equalsTester.assertEqual( new UserStatisticsChangeRequestDto(), new UserStatisticsChangeRequestDto() );
        equalsTester.assertNotEqual( userStatisticsChangeRequestDtoTest, userChangeRequestDto1);
    }
}