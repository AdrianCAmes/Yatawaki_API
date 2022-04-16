package com.orchestrator.orchestrator.utils.impl;

import com.orchestrator.orchestrator.model.Concert;
import com.orchestrator.orchestrator.model.Symphony;
import com.orchestrator.orchestrator.model.User;
import com.orchestrator.orchestrator.model.dto.concert.request.ConcertChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.concert.request.ConcertCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.concert.request.ConcertUpdateRequestDto;
import com.orchestrator.orchestrator.utils.ConcertUtils;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.constants.ConcertStatusConstants;
import com.orchestrator.orchestrator.utils.constants.NumericConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ConcertUtilsImpl implements ConcertUtils {
    private final GeneralUtils generalUtils;

    @Override
    public Concert buildDomainFromCreateRequestDto(ConcertCreateRequestDto concertCreateRequestDto) throws IllegalAccessException {
        User user = new User();
        user.setIdUser(concertCreateRequestDto.getIdUser());
        Symphony symphony = new Symphony();
        symphony.setIdUnlockable(concertCreateRequestDto.getIdSymphony());
        Concert concert = new Concert();
        concert.setUser(user);
        concert.setSymphony(symphony);
        concert.setPlayedDate(LocalDate.now());
        concert.setPoints(NumericConstants.ZERO.getValue());
        concert.setAccuracyRate(NumericConstants.ZERO.getValue().doubleValue());
        concert.setGesturesCompleted(NumericConstants.ZERO.getValue());
        concert.setStatus(ConcertStatusConstants.STARTED.getValue());
        generalUtils.mapFields(concertCreateRequestDto, concert);
        return concert;
    }

    @Override
    public Concert buildDomainFromUpdateRequestDto(ConcertUpdateRequestDto concertUpdateRequestDto) throws IllegalAccessException {
        Concert concert = new Concert();
        if (concertUpdateRequestDto.getIdUser() != null) {
            User user = new User();
            user.setIdUser(concertUpdateRequestDto.getIdUser());
            concert.setUser(user);
        }
        if (concertUpdateRequestDto.getIdSymphony() != null) {
            Symphony symphony = new Symphony();
            symphony.setIdUnlockable(concertUpdateRequestDto.getIdSymphony());
            concert.setSymphony(symphony);
        }
        generalUtils.mapFields(concertUpdateRequestDto, concert);
        return concert;
    }

    @Override
    public Concert buildDomainFromChangeRequestDto(ConcertChangeRequestDto concertChangeRequestDto) throws IllegalAccessException {
        User user = new User();
        user.setIdUser(concertChangeRequestDto.getIdUser());
        Symphony symphony = new Symphony();
        symphony.setIdUnlockable(concertChangeRequestDto.getIdSymphony());
        Concert concert = new Concert();
        concert.setUser(user);
        concert.setSymphony(symphony);
        generalUtils.mapFields(concertChangeRequestDto, concert);
        return concert;
    }

    @Override
    public ConcertCreateRequestDto buildCreateRequestDtoFromDomain(Concert concert) throws IllegalAccessException {
        ConcertCreateRequestDto concertCreateRequestDto = new ConcertCreateRequestDto();
        concertCreateRequestDto.setIdUser(concert.getUser().getIdUser());
        concertCreateRequestDto.setIdSymphony(concert.getSymphony().getIdUnlockable());
        generalUtils.mapFields(concert, concertCreateRequestDto);
        return concertCreateRequestDto;
    }

    @Override
    public ConcertUpdateRequestDto buildUpdateRequestDtoFromDomain(Concert concert) throws IllegalAccessException {
        ConcertUpdateRequestDto concertUpdateRequestDto = new ConcertUpdateRequestDto();
        concertUpdateRequestDto.setIdUser(concert.getUser().getIdUser());
        concertUpdateRequestDto.setIdSymphony(concert.getSymphony().getIdUnlockable());
        generalUtils.mapFields(concert, concertUpdateRequestDto);
        return concertUpdateRequestDto;
    }

    @Override
    public ConcertChangeRequestDto buildChangeRequestDtoFromDomain(Concert concert) throws IllegalAccessException {
        ConcertChangeRequestDto concertChangeRequestDto = new ConcertChangeRequestDto();
        concertChangeRequestDto.setIdUser(concert.getUser().getIdUser());
        concertChangeRequestDto.setIdSymphony(concert.getSymphony().getIdUnlockable());
        generalUtils.mapFields(concert, concertChangeRequestDto);
        return concertChangeRequestDto;
    }
}
