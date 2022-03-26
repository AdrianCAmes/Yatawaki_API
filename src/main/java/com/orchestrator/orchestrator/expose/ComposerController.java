package com.orchestrator.orchestrator.expose;

import com.orchestrator.orchestrator.business.ComposerService;
import com.orchestrator.orchestrator.model.Composer;
import com.orchestrator.orchestrator.model.dto.composer.request.ComposerChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.composer.request.ComposerCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.composer.request.ComposerUpdateRequestDto;
import com.orchestrator.orchestrator.utils.ComposerUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/composer")
public class ComposerController {
    private final ComposerService composerService;
    private final ComposerUtils composerUtils;

    // region CRUD Operations
    @PostMapping
    public ResponseEntity<Object> createComposer(@RequestBody ComposerCreateRequestDto composerCreateRequestDto) {
        log.info("Post operation in /composer");
        try {
            Composer composerToSave = composerUtils.buildDomainFromCreateRequestDto(composerCreateRequestDto);
            Composer savedComposer = composerService.create(composerToSave);
            return new ResponseEntity<>(savedComposer, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findComposerById(@PathVariable("id") Long id) {
        log.info("Get operation in /composer/{}", id);
        try {
            Composer retrievedComposer = composerService.findById(id);
            if (retrievedComposer != null) {
                return new ResponseEntity<>(retrievedComposer, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Composer not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllComposers(){
        log.info("Get operation in /composer");
        try {
            List<Composer> retrievedComposers = composerService.findAll();
            if (!retrievedComposers.isEmpty()) {
                return new ResponseEntity<>(retrievedComposers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Composers not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> changeComposer(@RequestBody ComposerChangeRequestDto composerChangeRequestDto) {
        log.info("Put operation in /composer");
        try {
            Composer composerToChange = composerUtils.buildDomainFromChangeRequestDto(composerChangeRequestDto);
            Composer changedComposer = composerService.change(composerToChange);
            return new ResponseEntity<>(changedComposer, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateComposer(@RequestBody ComposerUpdateRequestDto composerUpdateRequestDto) {
        log.info("Patch operation in /composer");
        try {
            Composer composerToUpdate = composerUtils.buildDomainFromUpdateRequestDto(composerUpdateRequestDto);
            Composer updatedComposer = composerService.update(composerToUpdate);
            return new ResponseEntity<>(updatedComposer, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteComposer(@PathVariable("id") Long id) {
        log.info("Delete operation in /composer/{}", id);
        try {
            Composer composer = composerService.removeById(id);
            return new ResponseEntity<>(composer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
