package com.orchestrator.orchestrator.model.dto.userstatistics.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;
import com.orchestrator.orchestrator.model.dto.EqualsTester;
import org.junit.jupiter.api.Test;

public class UserStatisticsUpdateRequestDtoTest extends DtoTest<UserStatisticsUpdateRequestDto> {
    @Override
    protected UserStatisticsUpdateRequestDto getInstance() {
        return new UserStatisticsUpdateRequestDto();
    }

    @Test
    public void testEqualsAndHashCode() {
        UserStatisticsUpdateRequestDto userStatisticsUpdateRequestDtoTest = new UserStatisticsUpdateRequestDto();
        userStatisticsUpdateRequestDtoTest.setIdUserStatistics(1L);

        UserStatisticsUpdateRequestDto userChangeRequestDto1 = new UserStatisticsUpdateRequestDto();
        userChangeRequestDto1.setIdUserStatistics(2L);
        EqualsTester<UserStatisticsUpdateRequestDto> equalsTester = EqualsTester.newInstance( new UserStatisticsUpdateRequestDto() );
        equalsTester.assertEqual( new UserStatisticsUpdateRequestDto(), new UserStatisticsUpdateRequestDto() );
        equalsTester.assertNotEqual( userStatisticsUpdateRequestDtoTest, userChangeRequestDto1);
    }
}