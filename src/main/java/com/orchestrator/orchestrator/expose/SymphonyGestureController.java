package com.orchestrator.orchestrator.expose;

import com.orchestrator.orchestrator.business.SymphonyGestureService;
import com.orchestrator.orchestrator.model.SymphonyGesture;
import com.orchestrator.orchestrator.model.dto.symphonygesture.request.SymphonyGestureChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.symphonygesture.request.SymphonyGestureCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.symphonygesture.request.SymphonyGestureUpdateRequestDto;
import com.orchestrator.orchestrator.utils.SymphonyGestureUtils;
import com.orchestrator.orchestrator.utils.constants.SymphonyGestureStatusConstants;
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
@RequestMapping("/api/v1/symphony-gesture")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class SymphonyGestureController {
    private final SymphonyGestureService symphonyGestureService;
    private final SymphonyGestureUtils symphonyGestureUtils;

    // region CRUD Operations
    @PostMapping
    public ResponseEntity<Object> createSymphonyGesture(@RequestBody SymphonyGestureCreateRequestDto symphonyGestureCreateRequestDto) {
        log.info("Post operation in /symphony-gesture");
        try {
            SymphonyGesture symphonyGestureToSave = symphonyGestureUtils.buildDomainFromCreateRequestDto(symphonyGestureCreateRequestDto);
            SymphonyGesture savedSymphonyGesture = symphonyGestureService.create(symphonyGestureToSave);
            return new ResponseEntity<>(savedSymphonyGesture, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findSymphonyGestureById(@PathVariable("id") Long id) {
        log.info("Get operation in /symphony-gesture/{}", id);
        try {
            SymphonyGesture retrievedSymphonyGesture = symphonyGestureService.findById(id);
            if (retrievedSymphonyGesture != null) {
                return new ResponseEntity<>(retrievedSymphonyGesture, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Symphony Gesture not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllSymphonyGestures(){
        log.info("Get operation in /symphony-gesture");
        try {
            List<SymphonyGesture> retrievedSymphonyGestures = symphonyGestureService.findAll();
            if (!retrievedSymphonyGestures.isEmpty()) {
                return new ResponseEntity<>(retrievedSymphonyGestures, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Symphony Gestures not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> changeSymphonyGesture(@RequestBody SymphonyGestureChangeRequestDto symphonyGestureChangeRequestDto) {
        log.info("Put operation in /symphony-gesture");
        try {
            SymphonyGesture symphonyGestureToChange = symphonyGestureUtils.buildDomainFromChangeRequestDto(symphonyGestureChangeRequestDto);
            SymphonyGesture changedSymphonyGesture = symphonyGestureService.change(symphonyGestureToChange);
            return new ResponseEntity<>(changedSymphonyGesture, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateSymphonyGesture(@RequestBody SymphonyGestureUpdateRequestDto symphonyGestureUpdateRequestDto) {
        log.info("Patch operation in /symphony-gesture");
        try {
            SymphonyGesture symphonyGestureToUpdate = symphonyGestureUtils.buildDomainFromUpdateRequestDto(symphonyGestureUpdateRequestDto);
            SymphonyGesture updatedSymphonyGesture = symphonyGestureService.update(symphonyGestureToUpdate);
            return new ResponseEntity<>(updatedSymphonyGesture, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSymphonyGesture(@PathVariable("id") Long id) {
        log.info("Delete operation in /symphony-gesture/{}", id);
        try {
            SymphonyGesture symphonyGesture = symphonyGestureService.removeById(id);
            return new ResponseEntity<>(symphonyGesture, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    @GetMapping("/status")
    public ResponseEntity<Object> getPossibleStatus() {
        log.info("Get operation in /symphony-gesture/status");
        try {
            List<SymphonyGestureStatusConstants> possibleStatus = symphonyGestureService.getPossibleStatus();
            return new ResponseEntity<>(possibleStatus, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion Use Cases
}
