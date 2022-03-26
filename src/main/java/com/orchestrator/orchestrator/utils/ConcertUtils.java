package com.orchestrator.orchestrator.utils;

import com.orchestrator.orchestrator.model.Concert;
import com.orchestrator.orchestrator.model.dto.concert.request.ConcertChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.concert.request.ConcertCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.concert.request.ConcertUpdateRequestDto;

public interface ConcertUtils {
    Concert buildDomainFromCreateRequestDto(ConcertCreateRequestDto concertCreateRequestDto) throws IllegalAccessException;
    Concert buildDomainFromUpdateRequestDto(ConcertUpdateRequestDto concertUpdateRequestDto) throws IllegalAccessException;
    Concert buildDomainFromChangeRequestDto(ConcertChangeRequestDto concertChangeRequestDto) throws IllegalAccessException;
    ConcertCreateRequestDto buildCreateRequestDtoFromDomain(Concert concert) throws IllegalAccessException;
    ConcertUpdateRequestDto buildUpdateRequestDtoFromDomain(Concert concert) throws IllegalAccessException;
    ConcertChangeRequestDto buildChangeRequestDtoFromDomain(Concert concert) throws IllegalAccessException;
}
