package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.GameService;
import com.orchestrator.orchestrator.model.Game;
import com.orchestrator.orchestrator.repository.GameRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    // Self repository
    private final GameRepository gameRepository;
    // Utils
    private final GeneralUtils generalUtils;

    // region CRUD Operations
    @Override
    public Game create(Game game) {
        if (game.getIdGame() != null) throw new IllegalArgumentException("Body should not contain id");
        return gameRepository.save(game);
    }

    @Override
    public Game findById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Game change(Game game) {
        Game gameToChange = findById(game.getIdGame());
        if (gameToChange != null) {
            return gameRepository.save(game);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Game update(Game game) throws IllegalAccessException {
        Game gameToUpdate = findById(game.getIdGame());
        if (gameToUpdate != null) {
            generalUtils.mapFields(game, gameToUpdate);
            return gameRepository.save(gameToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Game removeById(Long id) {
        Game gameToDelete = findById(id);
        if (gameToDelete != null) {
            gameRepository.deleteById(id);
            return gameToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases External
    // endregion Use Cases External

    // region Use Cases Internal
    // endregion Use Cases Internal
}
