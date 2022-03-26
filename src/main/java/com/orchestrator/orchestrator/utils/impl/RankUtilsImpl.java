package com.orchestrator.orchestrator.utils.impl;

import com.orchestrator.orchestrator.model.Rank;
import com.orchestrator.orchestrator.model.dto.rank.request.RankChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.rank.request.RankCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.rank.request.RankUpdateRequestDto;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.RankUtils;
import com.orchestrator.orchestrator.utils.constants.RankStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RankUtilsImpl implements RankUtils {
    private final GeneralUtils generalUtils;

    @Override
    public Rank buildDomainFromCreateRequestDto(RankCreateRequestDto rankCreateRequestDto) throws IllegalAccessException {
        Rank rank = new Rank();
        rank.setStatus(RankStatusConstants.ACTIVE.getValue());
        generalUtils.mapFields(rankCreateRequestDto, rank);
        return rank;
    }

    @Override
    public Rank buildDomainFromUpdateRequestDto(RankUpdateRequestDto rankUpdateRequestDto) throws IllegalAccessException {
        Rank rank = new Rank();
        generalUtils.mapFields(rankUpdateRequestDto, rank);
        return rank;
    }

    @Override
    public Rank buildDomainFromChangeRequestDto(RankChangeRequestDto rankChangeRequestDto) throws IllegalAccessException {
        Rank rank = new Rank();
        generalUtils.mapFields(rankChangeRequestDto, rank);
        return rank;
    }

    @Override
    public RankCreateRequestDto buildCreateRequestDtoFromDomain(Rank rank) throws IllegalAccessException {
        RankCreateRequestDto rankCreateRequestDto = new RankCreateRequestDto();
        generalUtils.mapFields(rank, rankCreateRequestDto);
        return rankCreateRequestDto;
    }

    @Override
    public RankUpdateRequestDto buildUpdateRequestDtoFromDomain(Rank rank) throws IllegalAccessException {
        RankUpdateRequestDto rankUpdateRequestDto = new RankUpdateRequestDto();
        generalUtils.mapFields(rank, rankUpdateRequestDto);
        return rankUpdateRequestDto;
    }

    @Override
    public RankChangeRequestDto buildChangeRequestDtoFromDomain(Rank rank) throws IllegalAccessException {
        RankChangeRequestDto rankChangeRequestDto = new RankChangeRequestDto();
        generalUtils.mapFields(rank, rankChangeRequestDto);
        return rankChangeRequestDto;
    }
}
