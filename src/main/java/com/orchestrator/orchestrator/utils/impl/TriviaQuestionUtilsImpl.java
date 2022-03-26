package com.orchestrator.orchestrator.utils.impl;

import com.orchestrator.orchestrator.model.Question;
import com.orchestrator.orchestrator.model.Trivia;
import com.orchestrator.orchestrator.model.TriviaQuestion;
import com.orchestrator.orchestrator.model.dto.triviaquestion.request.TriviaQuestionChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.triviaquestion.request.TriviaQuestionCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.triviaquestion.request.TriviaQuestionUpdateRequestDto;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.TriviaQuestionUtils;
import com.orchestrator.orchestrator.utils.constants.TriviaQuestionStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TriviaQuestionUtilsImpl implements TriviaQuestionUtils {
    private final GeneralUtils generalUtils;

    @Override
    public TriviaQuestion buildDomainFromCreateRequestDto(TriviaQuestionCreateRequestDto triviaQuestionCreateRequestDto) throws IllegalAccessException {
        Trivia trivia = new Trivia();
        trivia.setIdGame(triviaQuestionCreateRequestDto.getIdTrivia());
        Question question = new Question();
        question.setIdQuestion(triviaQuestionCreateRequestDto.getIdQuestion());
        TriviaQuestion triviaQuestion = new TriviaQuestion();
        triviaQuestion.setTrivia(trivia);
        triviaQuestion.setQuestion(question);
        triviaQuestion.setStatus(TriviaQuestionStatusConstants.ACTIVE.getValue());
        generalUtils.mapFields(triviaQuestionCreateRequestDto, triviaQuestion);
        return triviaQuestion;
    }

    @Override
    public TriviaQuestion buildDomainFromUpdateRequestDto(TriviaQuestionUpdateRequestDto triviaQuestionUpdateRequestDto) throws IllegalAccessException {
        Trivia trivia = new Trivia();
        trivia.setIdGame(triviaQuestionUpdateRequestDto.getIdTrivia());
        Question question = new Question();
        question.setIdQuestion(triviaQuestionUpdateRequestDto.getIdQuestion());
        TriviaQuestion triviaQuestion = new TriviaQuestion();
        triviaQuestion.setTrivia(trivia);
        triviaQuestion.setQuestion(question);
        generalUtils.mapFields(triviaQuestionUpdateRequestDto, triviaQuestion);
        return triviaQuestion;
    }

    @Override
    public TriviaQuestion buildDomainFromChangeRequestDto(TriviaQuestionChangeRequestDto triviaQuestionChangeRequestDto) throws IllegalAccessException {
        Trivia trivia = new Trivia();
        trivia.setIdGame(triviaQuestionChangeRequestDto.getIdTrivia());
        Question question = new Question();
        question.setIdQuestion(triviaQuestionChangeRequestDto.getIdQuestion());
        TriviaQuestion triviaQuestion = new TriviaQuestion();
        triviaQuestion.setTrivia(trivia);
        triviaQuestion.setQuestion(question);
        generalUtils.mapFields(triviaQuestionChangeRequestDto, triviaQuestion);
        return triviaQuestion;
    }

    @Override
    public TriviaQuestionCreateRequestDto buildCreateRequestDtoFromDomain(TriviaQuestion triviaQuestion) throws IllegalAccessException {
        TriviaQuestionCreateRequestDto triviaQuestionCreateRequestDto = new TriviaQuestionCreateRequestDto();
        triviaQuestionCreateRequestDto.setIdTrivia(triviaQuestion.getTrivia().getIdGame());
        triviaQuestionCreateRequestDto.setIdQuestion(triviaQuestion.getQuestion().getIdQuestion());
        generalUtils.mapFields(triviaQuestion, triviaQuestionCreateRequestDto);
        return triviaQuestionCreateRequestDto;
    }

    @Override
    public TriviaQuestionUpdateRequestDto buildUpdateRequestDtoFromDomain(TriviaQuestion triviaQuestion) throws IllegalAccessException {
        TriviaQuestionUpdateRequestDto triviaQuestionUpdateRequestDto = new TriviaQuestionUpdateRequestDto();
        triviaQuestionUpdateRequestDto.setIdTrivia(triviaQuestion.getTrivia().getIdGame());
        triviaQuestionUpdateRequestDto.setIdQuestion(triviaQuestion.getQuestion().getIdQuestion());
        generalUtils.mapFields(triviaQuestion, triviaQuestionUpdateRequestDto);
        return triviaQuestionUpdateRequestDto;
    }

    @Override
    public TriviaQuestionChangeRequestDto buildChangeRequestDtoFromDomain(TriviaQuestion triviaQuestion) throws IllegalAccessException {
        TriviaQuestionChangeRequestDto triviaQuestionChangeRequestDto = new TriviaQuestionChangeRequestDto();
        triviaQuestionChangeRequestDto.setIdTrivia(triviaQuestion.getTrivia().getIdGame());
        triviaQuestionChangeRequestDto.setIdQuestion(triviaQuestion.getQuestion().getIdQuestion());
        generalUtils.mapFields(triviaQuestion, triviaQuestionChangeRequestDto);
        return triviaQuestionChangeRequestDto;
    }
}
