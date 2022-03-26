package com.orchestrator.orchestrator.utils;

import com.orchestrator.orchestrator.model.Trivia;
import com.orchestrator.orchestrator.model.dto.trivia.request.TriviaChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.trivia.request.TriviaCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.trivia.request.TriviaUpdateRequestDto;

public interface TriviaUtils {
    Trivia buildDomainFromCreateRequestDto(TriviaCreateRequestDto triviaCreateRequestDto) throws IllegalAccessException;
    Trivia buildDomainFromUpdateRequestDto(TriviaUpdateRequestDto triviaUpdateRequestDto) throws IllegalAccessException;
    Trivia buildDomainFromChangeRequestDto(TriviaChangeRequestDto triviaChangeRequestDto) throws IllegalAccessException;
    TriviaCreateRequestDto buildCreateRequestDtoFromDomain(Trivia trivia) throws IllegalAccessException;
    TriviaUpdateRequestDto buildUpdateRequestDtoFromDomain(Trivia trivia) throws IllegalAccessException;
    TriviaChangeRequestDto buildChangeRequestDtoFromDomain(Trivia trivia) throws IllegalAccessException;
}
