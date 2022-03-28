package com.orchestrator.orchestrator.expose;

import com.orchestrator.orchestrator.business.TriviaService;
import com.orchestrator.orchestrator.model.Trivia;
import com.orchestrator.orchestrator.model.dto.trivia.request.TriviaChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.trivia.request.TriviaCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.trivia.request.TriviaOpenRequestDto;
import com.orchestrator.orchestrator.model.dto.trivia.request.TriviaUpdateRequestDto;
import com.orchestrator.orchestrator.utils.TriviaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/trivia")
public class TriviaController {
    private final TriviaService triviaService;
    private final TriviaUtils triviaUtils;

    // region CRUD Operations
    @PostMapping
    public ResponseEntity<Object> createTrivia(@RequestBody TriviaCreateRequestDto triviaCreateRequestDto) {
        log.info("Post operation in /trivia");
        try {
            Trivia triviaToSave = triviaUtils.buildDomainFromCreateRequestDto(triviaCreateRequestDto);
            Trivia savedTrivia = triviaService.create(triviaToSave);
            return new ResponseEntity<>(savedTrivia, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findTriviaById(@PathVariable("id") Long id) {
        log.info("Get operation in /trivia/{}", id);
        try {
            Trivia retrievedTrivia = triviaService.findById(id);
            if (retrievedTrivia != null) {
                return new ResponseEntity<>(retrievedTrivia, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Trivia not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllTrivias(){
        log.info("Get operation in /trivia");
        try {
            List<Trivia> retrievedTrivias = triviaService.findAll();
            if (!retrievedTrivias.isEmpty()) {
                return new ResponseEntity<>(retrievedTrivias, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Trivias not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> changeTrivia(@RequestBody TriviaChangeRequestDto triviaChangeRequestDto) {
        log.info("Put operation in /trivia");
        try {
            Trivia triviaToChange = triviaUtils.buildDomainFromChangeRequestDto(triviaChangeRequestDto);
            Trivia changedTrivia = triviaService.change(triviaToChange);
            return new ResponseEntity<>(changedTrivia, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateTrivia(@RequestBody TriviaUpdateRequestDto triviaUpdateRequestDto) {
        log.info("Patch operation in /trivia");
        try {
            Trivia triviaToUpdate = triviaUtils.buildDomainFromUpdateRequestDto(triviaUpdateRequestDto);
            Trivia updatedTrivia = triviaService.update(triviaToUpdate);
            return new ResponseEntity<>(updatedTrivia, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTrivia(@PathVariable("id") Long id) {
        log.info("Delete operation in /trivia/{}", id);
        try {
            Trivia trivia = triviaService.removeById(id);
            return new ResponseEntity<>(trivia, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    @PostMapping("/open")
    public ResponseEntity<Object> openTrivia(@RequestBody TriviaOpenRequestDto triviaOpenRequestDto) {
        log.info("Post operation in /trivia/open");
        try {
            Trivia savedTrivia = triviaService.openTrivia(triviaOpenRequestDto);
            return new ResponseEntity<>(savedTrivia, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion Use Cases
}
