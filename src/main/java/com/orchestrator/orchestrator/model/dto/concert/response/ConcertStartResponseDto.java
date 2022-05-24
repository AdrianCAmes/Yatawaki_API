package com.orchestrator.orchestrator.model.dto.concert.response;

import com.orchestrator.orchestrator.model.dto.instrument.response.InstrumentStartResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class ConcertStartResponseDto {
    private Long idConcert;
    private String name;
    private Integer initialBpm;
    private Integer duration;
    private List<InstrumentStartResponseDto> instruments;
}