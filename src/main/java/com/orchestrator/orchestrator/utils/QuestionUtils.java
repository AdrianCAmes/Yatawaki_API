package com.orchestrator.orchestrator.utils;

import com.orchestrator.orchestrator.model.Question;
import com.orchestrator.orchestrator.model.dto.question.request.QuestionChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.question.request.QuestionCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.question.request.QuestionUpdateRequestDto;

public interface QuestionUtils {
    Question buildDomainFromCreateRequestDto(QuestionCreateRequestDto questionCreateRequestDto) throws IllegalAccessException;
    Question buildDomainFromUpdateRequestDto(QuestionUpdateRequestDto questionUpdateRequestDto) throws IllegalAccessException;
    Question buildDomainFromChangeRequestDto(QuestionChangeRequestDto questionChangeRequestDto) throws IllegalAccessException;
    QuestionCreateRequestDto buildCreateRequestDtoFromDomain(Question question) throws IllegalAccessException;
    QuestionUpdateRequestDto buildUpdateRequestDtoFromDomain(Question question) throws IllegalAccessException;
    QuestionChangeRequestDto buildChangeRequestDtoFromDomain(Question question) throws IllegalAccessException;
}
