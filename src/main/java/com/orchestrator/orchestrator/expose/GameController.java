package com.orchestrator.orchestrator.expose;

import com.orchestrator.orchestrator.business.GameService;
import com.orchestrator.orchestrator.model.Game;
import com.orchestrator.orchestrator.model.dto.game.request.GameChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.game.request.GameCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.game.request.GameUpdateRequestDto;
import com.orchestrator.orchestrator.utils.GameUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/game")
public class GameController {
    private final GameService gameService;
    private final GameUtils gameUtils;

    // region CRUD Operations
    @PostMapping
    public ResponseEntity<Object> createGame(@RequestBody GameCreateRequestDto gameCreateRequestDto) {
        log.info("Post operation in /game");
        try {
            Game gameToSave = gameUtils.buildDomainFromCreateRequestDto(gameCreateRequestDto);
            Game savedGame = gameService.create(gameToSave);
            return new ResponseEntity<>(savedGame, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findGameById(@PathVariable("id") Long id) {
        log.info("Get operation in /game/{}", id);
        try {
            Game retrievedGame = gameService.findById(id);
            if (retrievedGame != null) {
                return new ResponseEntity<>(retrievedGame, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Game not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllGames(){
        log.info("Get operation in /game");
        try {
            List<Game> retrievedGames = gameService.findAll();
            if (!retrievedGames.isEmpty()) {
                return new ResponseEntity<>(retrievedGames, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Games not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> changeGame(@RequestBody GameChangeRequestDto gameChangeRequestDto) {
        log.info("Put operation in /game");
        try {
            Game gameToChange = gameUtils.buildDomainFromChangeRequestDto(gameChangeRequestDto);
            Game changedGame = gameService.change(gameToChange);
            return new ResponseEntity<>(changedGame, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateGame(@RequestBody GameUpdateRequestDto gameUpdateRequestDto) {
        log.info("Patch operation in /game");
        try {
            Game gameToUpdate = gameUtils.buildDomainFromUpdateRequestDto(gameUpdateRequestDto);
            Game updatedGame = gameService.update(gameToUpdate);
            return new ResponseEntity<>(updatedGame, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGame(@PathVariable("id") Long id) {
        log.info("Delete operation in /game/{}", id);
        try {
            Game game = gameService.removeById(id);
            return new ResponseEntity<>(game, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
