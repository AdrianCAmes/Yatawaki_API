package com.orchestrator.orchestrator.expose;

import com.orchestrator.orchestrator.business.GestureService;
import com.orchestrator.orchestrator.model.Gesture;
import com.orchestrator.orchestrator.model.dto.gesture.request.GestureChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.gesture.request.GestureCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.gesture.request.GestureUpdateRequestDto;
import com.orchestrator.orchestrator.utils.GestureUtils;
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
@RequestMapping("/api/v1/gesture")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class GestureController {
    private final GestureService gestureService;
    private final GestureUtils gestureUtils;

    // region CRUD Operations
    @PostMapping
    public ResponseEntity<Object> createGesture(@RequestBody GestureCreateRequestDto gestureCreateRequestDto) {
        log.info("Post operation in /gesture");
        try {
            Gesture gestureToSave = gestureUtils.buildDomainFromCreateRequestDto(gestureCreateRequestDto);
            Gesture savedGesture = gestureService.create(gestureToSave);
            return new ResponseEntity<>(savedGesture, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findGestureById(@PathVariable("id") Long id) {
        log.info("Get operation in /gesture/{}", id);
        try {
            Gesture retrievedGesture = gestureService.findById(id);
            if (retrievedGesture != null) {
                return new ResponseEntity<>(retrievedGesture, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Gesture not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllGestures(){
        log.info("Get operation in /gesture");
        try {
            List<Gesture> retrievedGestures = gestureService.findAll();
            if (!retrievedGestures.isEmpty()) {
                return new ResponseEntity<>(retrievedGestures, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Gestures not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> changeGesture(@RequestBody GestureChangeRequestDto gestureChangeRequestDto) {
        log.info("Put operation in /gesture");
        try {
            Gesture gestureToChange = gestureUtils.buildDomainFromChangeRequestDto(gestureChangeRequestDto);
            Gesture changedGesture = gestureService.change(gestureToChange);
            return new ResponseEntity<>(changedGesture, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateGesture(@RequestBody GestureUpdateRequestDto gestureUpdateRequestDto) {
        log.info("Patch operation in /gesture");
        try {
            Gesture gestureToUpdate = gestureUtils.buildDomainFromUpdateRequestDto(gestureUpdateRequestDto);
            Gesture updatedGesture = gestureService.update(gestureToUpdate);
            return new ResponseEntity<>(updatedGesture, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGesture(@PathVariable("id") Long id) {
        log.info("Delete operation in /gesture/{}", id);
        try {
            Gesture gesture = gestureService.removeById(id);
            return new ResponseEntity<>(gesture, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
