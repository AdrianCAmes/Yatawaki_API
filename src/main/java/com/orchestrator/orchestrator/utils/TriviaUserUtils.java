package com.orchestrator.orchestrator.utils;

import com.orchestrator.orchestrator.model.TriviaUser;
import com.orchestrator.orchestrator.model.dto.triviauser.request.TriviaUserChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.triviauser.request.TriviaUserCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.triviauser.request.TriviaUserUpdateRequestDto;

public interface TriviaUserUtils {
    TriviaUser buildDomainFromCreateRequestDto(TriviaUserCreateRequestDto triviaUserCreateRequestDto) throws IllegalAccessException;
    TriviaUser buildDomainFromUpdateRequestDto(TriviaUserUpdateRequestDto triviaUserUpdateRequestDto) throws IllegalAccessException;
    TriviaUser buildDomainFromChangeRequestDto(TriviaUserChangeRequestDto triviaUserChangeRequestDto) throws IllegalAccessException;
    TriviaUserCreateRequestDto buildCreateRequestDtoFromDomain(TriviaUser triviaUser) throws IllegalAccessException;
    TriviaUserUpdateRequestDto buildUpdateRequestDtoFromDomain(TriviaUser triviaUser) throws IllegalAccessException;
    TriviaUserChangeRequestDto buildChangeRequestDtoFromDomain(TriviaUser triviaUser) throws IllegalAccessException;
}
