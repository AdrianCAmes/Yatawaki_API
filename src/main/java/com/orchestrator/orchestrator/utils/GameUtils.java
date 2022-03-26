package com.orchestrator.orchestrator.utils;

import com.orchestrator.orchestrator.model.Game;
import com.orchestrator.orchestrator.model.dto.game.request.GameChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.game.request.GameCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.game.request.GameUpdateRequestDto;

public interface GameUtils {
    Game buildDomainFromCreateRequestDto(GameCreateRequestDto gameCreateRequestDto) throws IllegalAccessException;
    Game buildDomainFromUpdateRequestDto(GameUpdateRequestDto gameUpdateRequestDto) throws IllegalAccessException;
    Game buildDomainFromChangeRequestDto(GameChangeRequestDto gameChangeRequestDto) throws IllegalAccessException;
    GameCreateRequestDto buildCreateRequestDtoFromDomain(Game game) throws IllegalAccessException;
    GameUpdateRequestDto buildUpdateRequestDtoFromDomain(Game game) throws IllegalAccessException;
    GameChangeRequestDto buildChangeRequestDtoFromDomain(Game game) throws IllegalAccessException;
}
