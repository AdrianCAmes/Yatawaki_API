package com.orchestrator.orchestrator.expose;

import com.orchestrator.orchestrator.business.HiddenRecordService;
import com.orchestrator.orchestrator.model.HiddenRecord;
import com.orchestrator.orchestrator.model.dto.hiddenrecord.request.HiddenRecordChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.hiddenrecord.request.HiddenRecordCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.hiddenrecord.request.HiddenRecordUpdateRequestDto;
import com.orchestrator.orchestrator.utils.HiddenRecordUtils;
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
@RequestMapping("/api/v1/hidden-record")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class HiddenRecordController {
    private final HiddenRecordService hiddenRecordService;
    private final HiddenRecordUtils hiddenRecordUtils;

    // region CRUD Operations
    @PostMapping
    public ResponseEntity<Object> createHiddenRecord(@RequestBody HiddenRecordCreateRequestDto hiddenRecordCreateRequestDto) {
        log.info("Post operation in /hidden-record");
        try {
            HiddenRecord hiddenRecordToSave = hiddenRecordUtils.buildDomainFromCreateRequestDto(hiddenRecordCreateRequestDto);
            HiddenRecord savedHiddenRecord = hiddenRecordService.create(hiddenRecordToSave);
            return new ResponseEntity<>(savedHiddenRecord, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findHiddenRecordById(@PathVariable("id") Long id) {
        log.info("Get operation in /hidden-record/{}", id);
        try {
            HiddenRecord retrievedHiddenRecord = hiddenRecordService.findById(id);
            if (retrievedHiddenRecord != null) {
                return new ResponseEntity<>(retrievedHiddenRecord, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Hidden Record not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllHiddenRecords(){
        log.info("Get operation in /hidden-record");
        try {
            List<HiddenRecord> retrievedHiddenRecords = hiddenRecordService.findAll();
            if (!retrievedHiddenRecords.isEmpty()) {
                return new ResponseEntity<>(retrievedHiddenRecords, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Hidden Records not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> changeHiddenRecord(@RequestBody HiddenRecordChangeRequestDto hiddenRecordChangeRequestDto) {
        log.info("Put operation in /hidden-record");
        try {
            HiddenRecord hiddenRecordToChange = hiddenRecordUtils.buildDomainFromChangeRequestDto(hiddenRecordChangeRequestDto);
            HiddenRecord changedHiddenRecord = hiddenRecordService.change(hiddenRecordToChange);
            return new ResponseEntity<>(changedHiddenRecord, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateHiddenRecord(@RequestBody HiddenRecordUpdateRequestDto hiddenRecordUpdateRequestDto) {
        log.info("Patch operation in /hidden-record");
        try {
            HiddenRecord hiddenRecordToUpdate = hiddenRecordUtils.buildDomainFromUpdateRequestDto(hiddenRecordUpdateRequestDto);
            HiddenRecord updatedHiddenRecord = hiddenRecordService.update(hiddenRecordToUpdate);
            return new ResponseEntity<>(updatedHiddenRecord, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteHiddenRecord(@PathVariable("id") Long id) {
        log.info("Delete operation in /hidden-record/{}", id);
        try {
            HiddenRecord hiddenRecord = hiddenRecordService.removeById(id);
            return new ResponseEntity<>(hiddenRecord, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
