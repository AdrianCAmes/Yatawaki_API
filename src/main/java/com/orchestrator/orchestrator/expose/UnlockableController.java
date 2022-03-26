package com.orchestrator.orchestrator.expose;

import com.orchestrator.orchestrator.business.UnlockableService;
import com.orchestrator.orchestrator.model.Unlockable;
import com.orchestrator.orchestrator.model.dto.unlockable.request.UnlockableChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.unlockable.request.UnlockableCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.unlockable.request.UnlockableUpdateRequestDto;
import com.orchestrator.orchestrator.utils.UnlockableUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/unlockable")
public class UnlockableController {
    private final UnlockableService unlockableService;
    private final UnlockableUtils unlockableUtils;

    // region CRUD Operations
    @PostMapping
    public ResponseEntity<Object> createUnlockable(@RequestBody UnlockableCreateRequestDto unlockableCreateRequestDto) {
        log.info("Post operation in /unlockable");
        try {
            Unlockable unlockableToSave = unlockableUtils.buildDomainFromCreateRequestDto(unlockableCreateRequestDto);
            Unlockable savedUnlockable = unlockableService.create(unlockableToSave);
            return new ResponseEntity<>(savedUnlockable, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findUnlockableById(@PathVariable("id") Long id) {
        log.info("Get operation in /unlockable/{}", id);
        try {
            Unlockable retrievedUnlockable = unlockableService.findById(id);
            if (retrievedUnlockable != null) {
                return new ResponseEntity<>(retrievedUnlockable, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Unlockable not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllUnlockables(){
        log.info("Get operation in /unlockable");
        try {
            List<Unlockable> retrievedUnlockables = unlockableService.findAll();
            if (!retrievedUnlockables.isEmpty()) {
                return new ResponseEntity<>(retrievedUnlockables, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Unlockables not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> changeUnlockable(@RequestBody UnlockableChangeRequestDto unlockableChangeRequestDto) {
        log.info("Put operation in /unlockable");
        try {
            Unlockable unlockableToChange = unlockableUtils.buildDomainFromChangeRequestDto(unlockableChangeRequestDto);
            Unlockable changedUnlockable = unlockableService.change(unlockableToChange);
            return new ResponseEntity<>(changedUnlockable, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateUnlockable(@RequestBody UnlockableUpdateRequestDto unlockableUpdateRequestDto) {
        log.info("Patch operation in /unlockable");
        try {
            Unlockable unlockableToUpdate = unlockableUtils.buildDomainFromUpdateRequestDto(unlockableUpdateRequestDto);
            Unlockable updatedUnlockable = unlockableService.update(unlockableToUpdate);
            return new ResponseEntity<>(updatedUnlockable, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUnlockable(@PathVariable("id") Long id) {
        log.info("Delete operation in /unlockable/{}", id);
        try {
            Unlockable unlockable = unlockableService.removeById(id);
            return new ResponseEntity<>(unlockable, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
