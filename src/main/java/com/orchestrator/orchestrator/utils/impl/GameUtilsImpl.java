package com.orchestrator.orchestrator.utils.impl;

import com.orchestrator.orchestrator.model.Game;
import com.orchestrator.orchestrator.model.Symphony;
import com.orchestrator.orchestrator.model.dto.game.request.GameChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.game.request.GameCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.game.request.GameUpdateRequestDto;
import com.orchestrator.orchestrator.utils.GameUtils;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.constants.GameStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class GameUtilsImpl implements GameUtils {
    private final GeneralUtils generalUtils;

    @Override
    public Game buildDomainFromCreateRequestDto(GameCreateRequestDto gameCreateRequestDto) throws IllegalAccessException {
        Symphony symphony = new Symphony();
        symphony.setIdUnlockable(gameCreateRequestDto.getIdSymphony());
        Game game = new Game();
        game.setSymphony(symphony);
        game.setPlayedDate(LocalDate.now());
        game.setStatus(GameStatusConstants.ACTIVE.getValue());
        generalUtils.mapFields(gameCreateRequestDto, game);
        return game;
    }

    @Override
    public Game buildDomainFromUpdateRequestDto(GameUpdateRequestDto gameUpdateRequestDto) throws IllegalAccessException {
        Symphony symphony = new Symphony();
        symphony.setIdUnlockable(gameUpdateRequestDto.getIdSymphony());
        Game game = new Game();
        game.setSymphony(symphony);
        generalUtils.mapFields(gameUpdateRequestDto, game);
        return game;
    }

    @Override
    public Game buildDomainFromChangeRequestDto(GameChangeRequestDto gameChangeRequestDto) throws IllegalAccessException {
        Symphony symphony = new Symphony();
        symphony.setIdUnlockable(gameChangeRequestDto.getIdSymphony());
        Game game = new Game();
        game.setSymphony(symphony);
        generalUtils.mapFields(gameChangeRequestDto, game);
        return game;
    }

    @Override
    public GameCreateRequestDto buildCreateRequestDtoFromDomain(Game game) throws IllegalAccessException {
        GameCreateRequestDto gameCreateRequestDto = new GameCreateRequestDto();
        gameCreateRequestDto.setIdSymphony(game.getSymphony().getIdUnlockable());
        generalUtils.mapFields(game, gameCreateRequestDto);
        return gameCreateRequestDto;
    }

    @Override
    public GameUpdateRequestDto buildUpdateRequestDtoFromDomain(Game game) throws IllegalAccessException {
        GameUpdateRequestDto gameUpdateRequestDto = new GameUpdateRequestDto();
        gameUpdateRequestDto.setIdSymphony(game.getSymphony().getIdUnlockable());
        generalUtils.mapFields(game, gameUpdateRequestDto);
        return gameUpdateRequestDto;
    }

    @Override
    public GameChangeRequestDto buildChangeRequestDtoFromDomain(Game game) throws IllegalAccessException {
        GameChangeRequestDto gameChangeRequestDto = new GameChangeRequestDto();
        gameChangeRequestDto.setIdSymphony(game.getSymphony().getIdUnlockable());
        generalUtils.mapFields(game, gameChangeRequestDto);
        return gameChangeRequestDto;
    }
}
