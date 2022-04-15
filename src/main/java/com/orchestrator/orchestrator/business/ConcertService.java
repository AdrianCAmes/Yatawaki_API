package com.orchestrator.orchestrator.business;

import com.orchestrator.orchestrator.model.Concert;
import com.orchestrator.orchestrator.model.dto.concert.request.ConcertCompleteRequestDto;
import com.orchestrator.orchestrator.model.dto.concert.response.ConcertCompleteResponseDto;
import com.orchestrator.orchestrator.utils.constants.ConcertStatusConstants;

import java.util.List;

public interface ConcertService extends BaseService<Concert, Long> {
    ConcertCompleteResponseDto complete(ConcertCompleteRequestDto concertCompleteRequestDto) throws IllegalAccessException;
    List<ConcertStatusConstants> getPossibleStatus();
}
