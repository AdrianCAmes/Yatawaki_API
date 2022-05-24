package com.orchestrator.orchestrator.expose;

import com.orchestrator.orchestrator.business.ConcertService;
import com.orchestrator.orchestrator.model.Concert;
import com.orchestrator.orchestrator.model.dto.concert.request.ConcertChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.concert.request.ConcertCompleteRequestDto;
import com.orchestrator.orchestrator.model.dto.concert.request.ConcertCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.concert.request.ConcertUpdateRequestDto;
import com.orchestrator.orchestrator.model.dto.concert.response.ConcertCompleteResponseDto;
import com.orchestrator.orchestrator.model.dto.concert.response.ConcertStartResponseDto;
import com.orchestrator.orchestrator.utils.ConcertUtils;
import com.orchestrator.orchestrator.utils.constants.ConcertStatusConstants;
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
@RequestMapping("/api/v1/concert")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class ConcertController {
    private final ConcertService concertService;
    private final ConcertUtils concertUtils;

    // region CRUD Operations
    @PostMapping
    public ResponseEntity<Object> createConcert(@RequestBody ConcertCreateRequestDto concertCreateRequestDto) {
        log.info("Post operation in /concert");
        try {
            Concert concertToSave = concertUtils.buildDomainFromCreateRequestDto(concertCreateRequestDto);
            Concert savedConcert = concertService.create(concertToSave);
            return new ResponseEntity<>(savedConcert, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findConcertById(@PathVariable("id") Long id) {
        log.info("Get operation in /concert/{}", id);
        try {
            Concert retrievedConcert = concertService.findById(id);
            if (retrievedConcert != null) {
                return new ResponseEntity<>(retrievedConcert, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Concert not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllConcerts(){
        log.info("Get operation in /concert");
        try {
            List<Concert> retrievedConcerts = concertService.findAll();
            if (!retrievedConcerts.isEmpty()) {
                return new ResponseEntity<>(retrievedConcerts, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Concerts not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> changeConcert(@RequestBody ConcertChangeRequestDto concertTocChangeRequestDto) {
        log.info("Put operation in /concert");
        try {
            Concert concertToChange = concertUtils.buildDomainFromChangeRequestDto(concertTocChangeRequestDto);
            Concert changedConcert = concertService.change(concertToChange);
            return new ResponseEntity<>(changedConcert, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateConcert(@RequestBody ConcertUpdateRequestDto concertUpdateRequestDto) {
        log.info("Patch operation in /concert");
        try {
            Concert concertToUpdate = concertUtils.buildDomainFromUpdateRequestDto(concertUpdateRequestDto);
            Concert updatedConcert = concertService.update(concertToUpdate);
            return new ResponseEntity<>(updatedConcert, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteConcert(@PathVariable("id") Long id) {
        log.info("Delete operation in /concert/{}", id);
        try {
            Concert concert = concertService.removeById(id);
            return new ResponseEntity<>(concert, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    @PostMapping("/start")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PLAYER')")
    public ResponseEntity<Object> startConcert(@RequestBody ConcertCreateRequestDto concertCreateRequestDto) {
        log.info("Delete operation in /concert/complete");
        try {
            Concert concertToStart = concertUtils.buildDomainFromCreateRequestDto(concertCreateRequestDto);
            ConcertStartResponseDto concert = concertService.start(concertToStart);
            return new ResponseEntity<>(concert, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/complete")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PLAYER')")
    public ResponseEntity<Object> completeConcert(@RequestBody ConcertCompleteRequestDto concertCompleteRequestDto) {
        log.info("Delete operation in /concert/complete");
        try {
            ConcertCompleteResponseDto concert = concertService.complete(concertCompleteRequestDto);
            return new ResponseEntity<>(concert, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status")
    public ResponseEntity<Object> getPossibleStatus() {
        log.info("Get operation in /concert/status");
        try {
            List<ConcertStatusConstants> possibleStatus = concertService.getPossibleStatus();
            return new ResponseEntity<>(possibleStatus, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion Use Cases
}
