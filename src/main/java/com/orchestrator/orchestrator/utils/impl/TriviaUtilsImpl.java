package com.orchestrator.orchestrator.utils.impl;

import com.orchestrator.orchestrator.model.Symphony;
import com.orchestrator.orchestrator.model.Trivia;
import com.orchestrator.orchestrator.model.dto.trivia.request.TriviaChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.trivia.request.TriviaCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.trivia.request.TriviaUpdateRequestDto;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.TriviaUtils;
import com.orchestrator.orchestrator.utils.constants.GameStatusConstants;
import com.orchestrator.orchestrator.utils.constants.NumericConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class TriviaUtilsImpl implements TriviaUtils {
    private final GeneralUtils generalUtils;

    @Override
    public Trivia buildDomainFromCreateRequestDto(TriviaCreateRequestDto triviaCreateRequestDto) throws IllegalAccessException {
        Symphony symphony = new Symphony();
        symphony.setIdUnlockable(triviaCreateRequestDto.getIdSymphony());
        Trivia trivia = new Trivia();
        trivia.setSymphony(symphony);
        trivia.setPlayedDate(LocalDate.now());
        trivia.setNumberOfParticipants(NumericConstants.ONE.getValue());
        trivia.setStatus(GameStatusConstants.ACTIVE.getValue());
        generalUtils.mapFields(triviaCreateRequestDto, trivia);
        return trivia;
    }

    @Override
    public Trivia buildDomainFromUpdateRequestDto(TriviaUpdateRequestDto triviaUpdateRequestDto) throws IllegalAccessException {
        Symphony symphony = new Symphony();
        symphony.setIdUnlockable(triviaUpdateRequestDto.getIdSymphony());
        Trivia trivia = new Trivia();
        trivia.setSymphony(symphony);
        generalUtils.mapFields(triviaUpdateRequestDto, trivia);
        return trivia;
    }

    @Override
    public Trivia buildDomainFromChangeRequestDto(TriviaChangeRequestDto triviaChangeRequestDto) throws IllegalAccessException {
        Symphony symphony = new Symphony();
        symphony.setIdUnlockable(triviaChangeRequestDto.getIdSymphony());
        Trivia trivia = new Trivia();
        trivia.setSymphony(symphony);
        generalUtils.mapFields(triviaChangeRequestDto, trivia);
        return trivia;
    }

    @Override
    public TriviaCreateRequestDto buildCreateRequestDtoFromDomain(Trivia trivia) throws IllegalAccessException {
        TriviaCreateRequestDto triviaCreateRequestDto = new TriviaCreateRequestDto();
        triviaCreateRequestDto.setIdSymphony(trivia.getSymphony().getIdUnlockable());
        generalUtils.mapFields(trivia, triviaCreateRequestDto);
        return triviaCreateRequestDto;
    }

    @Override
    public TriviaUpdateRequestDto buildUpdateRequestDtoFromDomain(Trivia trivia) throws IllegalAccessException {
        TriviaUpdateRequestDto triviaUpdateRequestDto = new TriviaUpdateRequestDto();
        triviaUpdateRequestDto.setIdSymphony(trivia.getSymphony().getIdUnlockable());
        generalUtils.mapFields(trivia, triviaUpdateRequestDto);
        return triviaUpdateRequestDto;
    }

    @Override
    public TriviaChangeRequestDto buildChangeRequestDtoFromDomain(Trivia trivia) throws IllegalAccessException {
        TriviaChangeRequestDto triviaChangeRequestDto = new TriviaChangeRequestDto();
        triviaChangeRequestDto.setIdSymphony(trivia.getSymphony().getIdUnlockable());
        generalUtils.mapFields(trivia, triviaChangeRequestDto);
        return triviaChangeRequestDto;
    }
}
