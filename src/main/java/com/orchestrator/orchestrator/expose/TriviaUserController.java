package com.orchestrator.orchestrator.expose;

import com.orchestrator.orchestrator.business.TriviaUserService;
import com.orchestrator.orchestrator.model.TriviaUser;
import com.orchestrator.orchestrator.model.dto.triviauser.request.TriviaUserChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.triviauser.request.TriviaUserCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.triviauser.request.TriviaUserUpdateRequestDto;
import com.orchestrator.orchestrator.utils.TriviaUserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/trivia-user")
public class TriviaUserController {
    private final TriviaUserService triviaUserService;
    private final TriviaUserUtils triviaUserUtils;

    // region CRUD Operations
    @PostMapping
    public ResponseEntity<Object> createTriviaUser(@RequestBody TriviaUserCreateRequestDto triviaUserCreateRequestDto) {
        log.info("Post operation in /trivia-user");
        try {
            TriviaUser triviaUserToSave = triviaUserUtils.buildDomainFromCreateRequestDto(triviaUserCreateRequestDto);
            TriviaUser savedTriviaUser = triviaUserService.create(triviaUserToSave);
            return new ResponseEntity<>(savedTriviaUser, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findTriviaUserById(@PathVariable("id") Long id) {
        log.info("Get operation in /trivia-user/{}", id);
        try {
            TriviaUser retrievedTriviaUser = triviaUserService.findById(id);
            if (retrievedTriviaUser != null) {
                return new ResponseEntity<>(retrievedTriviaUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Trivia User not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllTriviaUsers(){
        log.info("Get operation in /trivia-user");
        try {
            List<TriviaUser> retrievedTriviaUsers = triviaUserService.findAll();
            if (!retrievedTriviaUsers.isEmpty()) {
                return new ResponseEntity<>(retrievedTriviaUsers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Trivia Users not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> changeTriviaUser(@RequestBody TriviaUserChangeRequestDto triviaUserChangeRequestDto) {
        log.info("Put operation in /trivia-user");
        try {
            TriviaUser triviaUserToChange = triviaUserUtils.buildDomainFromChangeRequestDto(triviaUserChangeRequestDto);
            TriviaUser changedTriviaUser = triviaUserService.change(triviaUserToChange);
            return new ResponseEntity<>(changedTriviaUser, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateTriviaUser(@RequestBody TriviaUserUpdateRequestDto triviaUserUpdateRequestDto) {
        log.info("Patch operation in /trivia-user");
        try {
            TriviaUser triviaUserToUpdate = triviaUserUtils.buildDomainFromUpdateRequestDto(triviaUserUpdateRequestDto);
            TriviaUser updatedTriviaUser = triviaUserService.update(triviaUserToUpdate);
            return new ResponseEntity<>(updatedTriviaUser, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTriviaUser(@PathVariable("id") Long id) {
        log.info("Delete operation in /trivia-user/{}", id);
        try {
            TriviaUser triviaUser = triviaUserService.removeById(id);
            return new ResponseEntity<>(triviaUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
