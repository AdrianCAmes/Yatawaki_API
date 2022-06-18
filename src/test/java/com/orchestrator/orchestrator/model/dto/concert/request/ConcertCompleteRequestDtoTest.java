package com.orchestrator.orchestrator.model.dto.concert.request;

import com.orchestrator.orchestrator.model.dto.DtoTest;
import com.orchestrator.orchestrator.model.dto.EqualsTester;
import org.junit.jupiter.api.Test;

public class ConcertCompleteRequestDtoTest extends DtoTest<ConcertCompleteRequestDto> {
    @Override
    protected ConcertCompleteRequestDto getInstance() {
        return new ConcertCompleteRequestDto();
    }

    @Test
    public void testEqualsAndHashCode() {
        ConcertCompleteRequestDto userUpdateRequestDto = new ConcertCompleteRequestDto();
        userUpdateRequestDto.setPoints(120);
        ConcertCompleteRequestDto userUpdateRequestDto1 = new ConcertCompleteRequestDto();
        userUpdateRequestDto1.setPoints(140);
        EqualsTester<ConcertCompleteRequestDto> equalsTester = EqualsTester.newInstance( new ConcertCompleteRequestDto() );
        equalsTester.assertEqual( new ConcertCompleteRequestDto(), new ConcertCompleteRequestDto() );
        equalsTester.assertNotEqual( userUpdateRequestDto, userUpdateRequestDto1);
        equalsTester.assertImplementsEqualsAndHashCode();
    }

}
