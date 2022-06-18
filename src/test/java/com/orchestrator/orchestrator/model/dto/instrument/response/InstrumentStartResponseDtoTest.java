package com.orchestrator.orchestrator.model.dto.instrument.response;

import com.orchestrator.orchestrator.model.dto.DtoTest;
import com.orchestrator.orchestrator.model.dto.EqualsTester;
import com.orchestrator.orchestrator.model.dto.user.request.UserChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.user.request.UserChangeRequestDtoTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InstrumentStartResponseDtoTest {

    @Test
    public void testEqualsAndHashCode() {
        InstrumentStartResponseDto instrumentStartResponseDto = new InstrumentStartResponseDto();
        instrumentStartResponseDto.setName("Hola");
        InstrumentStartResponseDto instrumentStartResponseDto1 = new InstrumentStartResponseDto();
        instrumentStartResponseDto.setName("Hola2");
        EqualsTester<InstrumentStartResponseDto> equalsTester = EqualsTester.newInstance( new InstrumentStartResponseDto() );
        equalsTester.assertEqual( new InstrumentStartResponseDto(), new InstrumentStartResponseDto() );
        equalsTester.assertNotEqual( instrumentStartResponseDto, instrumentStartResponseDto1);
    }

    @Test
    public void getterSetter(){
        InstrumentStartResponseDto instrumentStartResponseDto = new InstrumentStartResponseDto();
        instrumentStartResponseDto.setName("hola");
        instrumentStartResponseDto.setIcon("icon");
        instrumentStartResponseDto.setPosition("yes");
        instrumentStartResponseDto.setTrack("track");

        assertEquals(instrumentStartResponseDto.getIcon(), "icon");
        assertEquals(instrumentStartResponseDto.getName(), "hola");
        assertEquals(instrumentStartResponseDto.getPosition(), "yes");
        assertEquals(instrumentStartResponseDto.getTrack(), "track");
    }
}