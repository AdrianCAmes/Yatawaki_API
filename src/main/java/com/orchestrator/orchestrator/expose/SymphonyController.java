package com.orchestrator.orchestrator.expose;

import com.orchestrator.orchestrator.business.SymphonyService;
import com.orchestrator.orchestrator.model.Symphony;
import com.orchestrator.orchestrator.model.dto.symphony.request.SymphonyChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.symphony.request.SymphonyCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.symphony.request.SymphonyUpdateRequestDto;
import com.orchestrator.orchestrator.utils.SymphonyUtils;
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
@RequestMapping("/api/v1/symphony")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class SymphonyController {
    private final SymphonyService symphonyService;
    private final SymphonyUtils symphonyUtils;

    // region CRUD Operations
    @PostMapping
    public ResponseEntity<Object> createSymphony(@RequestBody SymphonyCreateRequestDto symphonyCreateRequestDto) {
        log.info("Post operation in /symphony");
        try {
            Symphony symphonyToSave = symphonyUtils.buildDomainFromCreateRequestDto(symphonyCreateRequestDto);
            Symphony savedSymphony = symphonyService.create(symphonyToSave);
            return new ResponseEntity<>(savedSymphony, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findSymphonyById(@PathVariable("id") Long id) {
        log.info("Get operation in /symphony/{}", id);
        try {
            Symphony retrievedSymphony = symphonyService.findById(id);
            if (retrievedSymphony != null) {
                return new ResponseEntity<>(retrievedSymphony, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Symphony not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllSymphonys(){
        log.info("Get operation in /symphony");
        try {
            List<Symphony> retrievedSymphonys = symphonyService.findAll();
            if (!retrievedSymphonys.isEmpty()) {
                return new ResponseEntity<>(retrievedSymphonys, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Symphonys not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> changeSymphony(@RequestBody SymphonyChangeRequestDto symphonyChangeRequestDto) {
        log.info("Put operation in /symphony");
        try {
            Symphony symphonyToChange = symphonyUtils.buildDomainFromChangeRequestDto(symphonyChangeRequestDto);
            Symphony changedSymphony = symphonyService.change(symphonyToChange);
            return new ResponseEntity<>(changedSymphony, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateSymphony(@RequestBody SymphonyUpdateRequestDto symphonyUpdateRequestDto) {
        log.info("Patch operation in /symphony");
        try {
            Symphony symphonyToUpdate = symphonyUtils.buildDomainFromUpdateRequestDto(symphonyUpdateRequestDto);
            Symphony updatedSymphony = symphonyService.update(symphonyToUpdate);
            return new ResponseEntity<>(updatedSymphony, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSymphony(@PathVariable("id") Long id) {
        log.info("Delete operation in /symphony/{}", id);
        try {
            Symphony symphony = symphonyService.removeById(id);
            return new ResponseEntity<>(symphony, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
