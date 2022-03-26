package com.orchestrator.orchestrator.utils.impl;

import com.orchestrator.orchestrator.model.Question;
import com.orchestrator.orchestrator.model.Rank;
import com.orchestrator.orchestrator.model.dto.question.request.QuestionChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.question.request.QuestionCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.question.request.QuestionUpdateRequestDto;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.QuestionUtils;
import com.orchestrator.orchestrator.utils.constants.QuestionStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionUtilsImpl implements QuestionUtils {
    private final GeneralUtils generalUtils;

    @Override
    public Question buildDomainFromCreateRequestDto(QuestionCreateRequestDto questionCreateRequestDto) throws IllegalAccessException {
        Rank rank = new Rank();
        rank.setIdRank(questionCreateRequestDto.getIdRank());
        Question question = new Question();
        question.setRank(rank);
        question.setStatus(QuestionStatusConstants.ACTIVE.getValue());
        generalUtils.mapFields(questionCreateRequestDto, question);
        return question;
    }

    @Override
    public Question buildDomainFromUpdateRequestDto(QuestionUpdateRequestDto questionUpdateRequestDto) throws IllegalAccessException {
        Rank rank = new Rank();
        rank.setIdRank(questionUpdateRequestDto.getIdRank());
        Question question = new Question();
        question.setRank(rank);
        generalUtils.mapFields(questionUpdateRequestDto, question);
        return question;
    }

    @Override
    public Question buildDomainFromChangeRequestDto(QuestionChangeRequestDto questionChangeRequestDto) throws IllegalAccessException {
        Rank rank = new Rank();
        rank.setIdRank(questionChangeRequestDto.getIdRank());
        Question question = new Question();
        question.setRank(rank);
        generalUtils.mapFields(questionChangeRequestDto, question);
        return question;
    }

    @Override
    public QuestionCreateRequestDto buildCreateRequestDtoFromDomain(Question question) throws IllegalAccessException {
        QuestionCreateRequestDto questionCreateRequestDto = new QuestionCreateRequestDto();
        questionCreateRequestDto.setIdRank(question.getRank().getIdRank());
        generalUtils.mapFields(question, questionCreateRequestDto);
        return questionCreateRequestDto;
    }

    @Override
    public QuestionUpdateRequestDto buildUpdateRequestDtoFromDomain(Question question) throws IllegalAccessException {
        QuestionUpdateRequestDto questionUpdateRequestDto = new QuestionUpdateRequestDto();
        questionUpdateRequestDto.setIdRank(question.getRank().getIdRank());
        generalUtils.mapFields(question, questionUpdateRequestDto);
        return questionUpdateRequestDto;
    }

    @Override
    public QuestionChangeRequestDto buildChangeRequestDtoFromDomain(Question question) throws IllegalAccessException {
        QuestionChangeRequestDto questionChangeRequestDto = new QuestionChangeRequestDto();
        questionChangeRequestDto.setIdRank(question.getRank().getIdRank());
        generalUtils.mapFields(question, questionChangeRequestDto);
        return questionChangeRequestDto;
    }
}
