package com.orchestrator.orchestrator.expose;

import com.orchestrator.orchestrator.business.InstrumentService;
import com.orchestrator.orchestrator.model.Instrument;
import com.orchestrator.orchestrator.model.dto.instrument.request.InstrumentChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.instrument.request.InstrumentCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.instrument.request.InstrumentUpdateRequestDto;
import com.orchestrator.orchestrator.utils.InstrumentUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/instrument")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class InstrumentController {
    private final InstrumentService instrumentService;
    private final InstrumentUtils instrumentUtils;

    // region CRUD Operations
    @PostMapping
    public ResponseEntity<Object> createInstrument(@RequestBody InstrumentCreateRequestDto instrumentCreateRequestDto) {
        log.info("Post operation in /instrument");
        try {
            Instrument instrumentToSave = instrumentUtils.buildDomainFromCreateRequestDto(instrumentCreateRequestDto);
            Instrument savedInstrument = instrumentService.create(instrumentToSave);
            return new ResponseEntity<>(savedInstrument, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findInstrumentById(@PathVariable("id") Long id) {
        log.info("Get operation in /instrument/{}", id);
        try {
            Instrument retrievedInstrument = instrumentService.findById(id);
            if (retrievedInstrument != null) {
                return new ResponseEntity<>(retrievedInstrument, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Instrument not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllInstruments(){
        log.info("Get operation in /instrument");
        try {
            List<Instrument> retrievedInstruments = instrumentService.findAll();
            if (!retrievedInstruments.isEmpty()) {
                return new ResponseEntity<>(retrievedInstruments, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Instruments not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> changeInstrument(@RequestBody InstrumentChangeRequestDto instrumentChangeRequestDto) {
        log.info("Put operation in /instrument");
        try {
            Instrument instrumentToChange = instrumentUtils.buildDomainFromChangeRequestDto(instrumentChangeRequestDto);
            Instrument changedInstrument = instrumentService.change(instrumentToChange);
            return new ResponseEntity<>(changedInstrument, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateInstrument(@RequestBody InstrumentUpdateRequestDto instrumentUpdateRequestDto) {
        log.info("Patch operation in /instrument");
        try {
            Instrument instrumentToUpdate = instrumentUtils.buildDomainFromUpdateRequestDto(instrumentUpdateRequestDto);
            Instrument updatedInstrument = instrumentService.update(instrumentToUpdate);
            return new ResponseEntity<>(updatedInstrument, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteInstrument(@PathVariable("id") Long id) {
        log.info("Delete operation in /instrument/{}", id);
        try {
            Instrument instrument = instrumentService.removeById(id);
            return new ResponseEntity<>(instrument, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    @PostMapping("/{id}/image-upload")
    public ResponseEntity<Object> uploadImage(@PathVariable("id") Long id, @RequestParam MultipartFile icon) {
        log.info("Delete operation in /instrument/{}/image-upload", id);
        try {
            Instrument instrumentToUpdate = instrumentService.findById(id);
            if (instrumentToUpdate != null) {
                instrumentToUpdate.setIcon(icon.getBytes());
                Instrument updatedInstrument = instrumentService.update(instrumentToUpdate);
                return new ResponseEntity<>(updatedInstrument, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Instrument not found", HttpStatus.NOT_FOUND);
            }
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    Resource downloadImage(@PathVariable Long id) {
        log.info("Delete operation in /instrument/{}/image", id);
        byte[] image = instrumentService.findById(id).getIcon();
        return new ByteArrayResource(image);
    }
    // endregion Use Cases
}
