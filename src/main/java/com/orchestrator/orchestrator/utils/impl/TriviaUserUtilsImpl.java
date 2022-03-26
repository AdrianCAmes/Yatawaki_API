package com.orchestrator.orchestrator.utils.impl;

import com.orchestrator.orchestrator.model.Trivia;
import com.orchestrator.orchestrator.model.TriviaUser;
import com.orchestrator.orchestrator.model.User;
import com.orchestrator.orchestrator.model.dto.triviauser.request.TriviaUserChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.triviauser.request.TriviaUserCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.triviauser.request.TriviaUserUpdateRequestDto;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.TriviaUserUtils;
import com.orchestrator.orchestrator.utils.constants.NumericConstants;
import com.orchestrator.orchestrator.utils.constants.StringConstants;
import com.orchestrator.orchestrator.utils.constants.TriviaUserStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TriviaUserUtilsImpl implements TriviaUserUtils {
    private final GeneralUtils generalUtils;

    @Override
    public TriviaUser buildDomainFromCreateRequestDto(TriviaUserCreateRequestDto triviaUserCreateRequestDto) throws IllegalAccessException {
        Trivia trivia = new Trivia();
        trivia.setIdGame(triviaUserCreateRequestDto.getIdTrivia());
        User user = new User();
        user.setIdUser(triviaUserCreateRequestDto.getIdUser());
        TriviaUser triviaUser = new TriviaUser();
        triviaUser.setTrivia(trivia);
        triviaUser.setUser(user);
        triviaUser.setPoints(NumericConstants.ZERO.getValue());
        triviaUser.setIsWinner(Boolean.FALSE);
        triviaUser.setAnsweredQuestionsJson(StringConstants.EMPTY.getValue());
        triviaUser.setAccuracyRate(NumericConstants.ZERO.getValue().doubleValue());
        triviaUser.setStatus(TriviaUserStatusConstants.ACTIVE.getValue());
        generalUtils.mapFields(triviaUserCreateRequestDto, triviaUser);
        return triviaUser;
    }

    @Override
    public TriviaUser buildDomainFromUpdateRequestDto(TriviaUserUpdateRequestDto triviaUserUpdateRequestDto) throws IllegalAccessException {
        Trivia trivia = new Trivia();
        trivia.setIdGame(triviaUserUpdateRequestDto.getIdTrivia());
        User user = new User();
        user.setIdUser(triviaUserUpdateRequestDto.getIdUser());
        TriviaUser triviaUser = new TriviaUser();
        triviaUser.setTrivia(trivia);
        triviaUser.setUser(user);
        generalUtils.mapFields(triviaUserUpdateRequestDto, triviaUser);
        return triviaUser;
    }

    @Override
    public TriviaUser buildDomainFromChangeRequestDto(TriviaUserChangeRequestDto triviaUserChangeRequestDto) throws IllegalAccessException {
        Trivia trivia = new Trivia();
        trivia.setIdGame(triviaUserChangeRequestDto.getIdTrivia());
        User user = new User();
        user.setIdUser(triviaUserChangeRequestDto.getIdUser());
        TriviaUser triviaUser = new TriviaUser();
        triviaUser.setTrivia(trivia);
        triviaUser.setUser(user);
        generalUtils.mapFields(triviaUserChangeRequestDto, triviaUser);
        return triviaUser;
    }

    @Override
    public TriviaUserCreateRequestDto buildCreateRequestDtoFromDomain(TriviaUser triviaUser) throws IllegalAccessException {
        TriviaUserCreateRequestDto triviaUserCreateRequestDto = new TriviaUserCreateRequestDto();
        triviaUserCreateRequestDto.setIdTrivia(triviaUser.getTrivia().getIdGame());
        triviaUserCreateRequestDto.setIdUser(triviaUser.getUser().getIdUser());
        generalUtils.mapFields(triviaUser, triviaUserCreateRequestDto);
        return triviaUserCreateRequestDto;
    }

    @Override
    public TriviaUserUpdateRequestDto buildUpdateRequestDtoFromDomain(TriviaUser triviaUser) throws IllegalAccessException {
        TriviaUserUpdateRequestDto triviaUserUpdateRequestDto = new TriviaUserUpdateRequestDto();
        triviaUserUpdateRequestDto.setIdTrivia(triviaUser.getTrivia().getIdGame());
        triviaUserUpdateRequestDto.setIdUser(triviaUser.getUser().getIdUser());
        generalUtils.mapFields(triviaUser, triviaUserUpdateRequestDto);
        return triviaUserUpdateRequestDto;
    }

    @Override
    public TriviaUserChangeRequestDto buildChangeRequestDtoFromDomain(TriviaUser triviaUser) throws IllegalAccessException {
        TriviaUserChangeRequestDto triviaUserChangeRequestDto = new TriviaUserChangeRequestDto();
        triviaUserChangeRequestDto.setIdTrivia(triviaUser.getTrivia().getIdGame());
        triviaUserChangeRequestDto.setIdUser(triviaUser.getUser().getIdUser());
        generalUtils.mapFields(triviaUser, triviaUserChangeRequestDto);
        return triviaUserChangeRequestDto;
    }
}
