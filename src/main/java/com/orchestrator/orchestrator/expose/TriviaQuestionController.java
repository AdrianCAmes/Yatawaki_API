package com.orchestrator.orchestrator.expose;

import com.orchestrator.orchestrator.business.TriviaQuestionService;
import com.orchestrator.orchestrator.model.TriviaQuestion;
import com.orchestrator.orchestrator.model.dto.triviaquestion.request.TriviaQuestionChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.triviaquestion.request.TriviaQuestionCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.triviaquestion.request.TriviaQuestionUpdateRequestDto;
import com.orchestrator.orchestrator.utils.TriviaQuestionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/trivia-question")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class TriviaQuestionController {
    private final TriviaQuestionService triviaQuestionService;
    private final TriviaQuestionUtils triviaQuestionUtils;

    // region CRUD Operations
    @PostMapping
    public ResponseEntity<Object> createTriviaQuestion(@RequestBody TriviaQuestionCreateRequestDto triviaQuestionCreateRequestDto) {
        log.info("Post operation in /trivia-question");
        try {
            TriviaQuestion triviaQuestionToSave = triviaQuestionUtils.buildDomainFromCreateRequestDto(triviaQuestionCreateRequestDto);
            TriviaQuestion savedTriviaQuestion = triviaQuestionService.create(triviaQuestionToSave);
            return new ResponseEntity<>(savedTriviaQuestion, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findTriviaQuestionById(@PathVariable("id") Long id) {
        log.info("Get operation in /trivia-question/{}", id);
        try {
            TriviaQuestion retrievedTriviaQuestion = triviaQuestionService.findById(id);
            if (retrievedTriviaQuestion != null) {
                return new ResponseEntity<>(retrievedTriviaQuestion, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Trivia Question not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllTriviaQuestions(){
        log.info("Get operation in /trivia-question");
        try {
            List<TriviaQuestion> retrievedTriviaQuestions = triviaQuestionService.findAll();
            if (!retrievedTriviaQuestions.isEmpty()) {
                return new ResponseEntity<>(retrievedTriviaQuestions, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Trivia Questions not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> changeTriviaQuestion(@RequestBody TriviaQuestionChangeRequestDto triviaQuestionChangeRequestDto) {
        log.info("Put operation in /trivia-question");
        try {
            TriviaQuestion triviaQuestionToChange = triviaQuestionUtils.buildDomainFromChangeRequestDto(triviaQuestionChangeRequestDto);
            TriviaQuestion changedTriviaQuestion = triviaQuestionService.change(triviaQuestionToChange);
            return new ResponseEntity<>(changedTriviaQuestion, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateTriviaQuestion(@RequestBody TriviaQuestionUpdateRequestDto triviaQuestionUpdateRequestDto) {
        log.info("Patch operation in /trivia-question");
        try {
            TriviaQuestion triviaQuestionToUpdate = triviaQuestionUtils.buildDomainFromUpdateRequestDto(triviaQuestionUpdateRequestDto);
            TriviaQuestion updatedTriviaQuestion = triviaQuestionService.update(triviaQuestionToUpdate);
            return new ResponseEntity<>(updatedTriviaQuestion, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTriviaQuestion(@PathVariable("id") Long id) {
        log.info("Delete operation in /trivia-question/{}", id);
        try {
            TriviaQuestion triviaQuestion = triviaQuestionService.removeById(id);
            return new ResponseEntity<>(triviaQuestion, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
