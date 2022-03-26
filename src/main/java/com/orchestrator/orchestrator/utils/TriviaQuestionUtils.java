package com.orchestrator.orchestrator.utils;

import com.orchestrator.orchestrator.model.TriviaQuestion;
import com.orchestrator.orchestrator.model.dto.triviaquestion.request.TriviaQuestionChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.triviaquestion.request.TriviaQuestionCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.triviaquestion.request.TriviaQuestionUpdateRequestDto;

public interface TriviaQuestionUtils {
    TriviaQuestion buildDomainFromCreateRequestDto(TriviaQuestionCreateRequestDto triviaQuestionCreateRequestDto) throws IllegalAccessException;
    TriviaQuestion buildDomainFromUpdateRequestDto(TriviaQuestionUpdateRequestDto triviaQuestionUpdateRequestDto) throws IllegalAccessException;
    TriviaQuestion buildDomainFromChangeRequestDto(TriviaQuestionChangeRequestDto triviaQuestionChangeRequestDto) throws IllegalAccessException;
    TriviaQuestionCreateRequestDto buildCreateRequestDtoFromDomain(TriviaQuestion triviaQuestion) throws IllegalAccessException;
    TriviaQuestionUpdateRequestDto buildUpdateRequestDtoFromDomain(TriviaQuestion triviaQuestion) throws IllegalAccessException;
    TriviaQuestionChangeRequestDto buildChangeRequestDtoFromDomain(TriviaQuestion triviaQuestion) throws IllegalAccessException;
}
