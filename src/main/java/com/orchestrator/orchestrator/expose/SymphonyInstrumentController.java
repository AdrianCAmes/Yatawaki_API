package com.orchestrator.orchestrator.expose;

import com.orchestrator.orchestrator.business.SymphonyInstrumentService;
import com.orchestrator.orchestrator.model.SymphonyInstrument;
import com.orchestrator.orchestrator.model.dto.symphonyinstrument.request.SymphonyInstrumentChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.symphonyinstrument.request.SymphonyInstrumentCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.symphonyinstrument.request.SymphonyInstrumentUpdateRequestDto;
import com.orchestrator.orchestrator.utils.SymphonyInstrumentUtils;
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
@RequestMapping("/api/v1/symphony-instrument")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class SymphonyInstrumentController {
    private final SymphonyInstrumentService symphonyInstrumentService;
    private final SymphonyInstrumentUtils symphonyInstrumentUtils;

    // region CRUD Operations
    @PostMapping
    public ResponseEntity<Object> createSymphonyInstrument(@RequestBody SymphonyInstrumentCreateRequestDto symphonyInstrumentCreateRequestDto) {
        log.info("Post operation in /symphony-instrument");
        try {
            SymphonyInstrument symphonyInstrumentToSave = symphonyInstrumentUtils.buildDomainFromCreateRequestDto(symphonyInstrumentCreateRequestDto);
            SymphonyInstrument savedSymphonyInstrument = symphonyInstrumentService.create(symphonyInstrumentToSave);
            return new ResponseEntity<>(savedSymphonyInstrument, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findSymphonyInstrumentById(@PathVariable("id") Long id) {
        log.info("Get operation in /symphony-instrument/{}", id);
        try {
            SymphonyInstrument retrievedSymphonyInstrument = symphonyInstrumentService.findById(id);
            if (retrievedSymphonyInstrument != null) {
                return new ResponseEntity<>(retrievedSymphonyInstrument, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Symphony Instrument not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllSymphonyInstruments(){
        log.info("Get operation in /symphony-instrument");
        try {
            List<SymphonyInstrument> retrievedSymphonyInstruments = symphonyInstrumentService.findAll();
            if (!retrievedSymphonyInstruments.isEmpty()) {
                return new ResponseEntity<>(retrievedSymphonyInstruments, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Symphony Instruments not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> changeSymphonyInstrument(@RequestBody SymphonyInstrumentChangeRequestDto symphonyInstrumentChangeRequestDto) {
        log.info("Put operation in /symphony-instrument");
        try {
            SymphonyInstrument symphonyInstrumentToChange = symphonyInstrumentUtils.buildDomainFromChangeRequestDto(symphonyInstrumentChangeRequestDto);
            SymphonyInstrument changedSymphonyInstrument = symphonyInstrumentService.change(symphonyInstrumentToChange);
            return new ResponseEntity<>(changedSymphonyInstrument, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateSymphonyInstrument(@RequestBody SymphonyInstrumentUpdateRequestDto symphonyInstrumentUpdateRequestDto) {
        log.info("Patch operation in /symphony-instrument");
        try {
            SymphonyInstrument symphonyInstrumentToUpdate = symphonyInstrumentUtils.buildDomainFromUpdateRequestDto(symphonyInstrumentUpdateRequestDto);
            SymphonyInstrument updatedSymphonyInstrument = symphonyInstrumentService.update(symphonyInstrumentToUpdate);
            return new ResponseEntity<>(updatedSymphonyInstrument, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSymphonyInstrument(@PathVariable("id") Long id) {
        log.info("Delete operation in /symphony-instrument/{}", id);
        try {
            SymphonyInstrument symphonyInstrument = symphonyInstrumentService.removeById(id);
            return new ResponseEntity<>(symphonyInstrument, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
