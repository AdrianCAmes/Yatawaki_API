package com.orchestrator.orchestrator.expose;

import com.orchestrator.orchestrator.business.UserStatisticsService;
import com.orchestrator.orchestrator.model.UserStatistics;
import com.orchestrator.orchestrator.model.dto.userstatistics.request.UserStatisticsChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.userstatistics.request.UserStatisticsCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.userstatistics.request.UserStatisticsUpdateRequestDto;
import com.orchestrator.orchestrator.utils.UserStatisticsUtils;
import com.orchestrator.orchestrator.utils.constants.UserStatisticsStatusConstants;
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
@RequestMapping("/api/v1/user-statistics")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserStatisticsController {
    private final UserStatisticsService userStatisticsService;
    private final UserStatisticsUtils userStatisticsUtils;

    // region CRUD Operations
    @PostMapping
    public ResponseEntity<Object> createUserStatistics(@RequestBody UserStatisticsCreateRequestDto userStatisticsCreateRequestDto) {
        log.info("Post operation in /user-statistics");
        try {
            UserStatistics userStatisticsToSave = userStatisticsUtils.buildDomainFromCreateRequestDto(userStatisticsCreateRequestDto);
            UserStatistics savedUserStatistics = userStatisticsService.create(userStatisticsToSave);
            return new ResponseEntity<>(savedUserStatistics, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PLAYER')")
    public ResponseEntity<Object> findUserStatisticsById(@PathVariable("id") Long id) {
        log.info("Get operation in /user-statistics/{}", id);
        try {
            UserStatistics retrievedUserStatistics = userStatisticsService.findById(id);
            if (retrievedUserStatistics != null) {
                return new ResponseEntity<>(retrievedUserStatistics, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User Statistics not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllUserStatistics(){
        log.info("Get operation in /user-statistics");
        try {
            List<UserStatistics> retrievedUserStatistics = userStatisticsService.findAll();
            if (!retrievedUserStatistics.isEmpty()) {
                return new ResponseEntity<>(retrievedUserStatistics, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User Statistics not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> changeUserStatistics(@RequestBody UserStatisticsChangeRequestDto userStatisticsChangeRequestDto) {
        log.info("Put operation in /user-statistics");
        try {
            UserStatistics userStatisticsToChange = userStatisticsUtils.buildDomainFromChangeRequestDto(userStatisticsChangeRequestDto);
            UserStatistics changedUserStatistics = userStatisticsService.change(userStatisticsToChange);
            return new ResponseEntity<>(changedUserStatistics, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateUserStatistics(@RequestBody UserStatisticsUpdateRequestDto userStatisticsUpdateRequestDto) {
        log.info("Patch operation in /user-statistics");
        try {
            UserStatistics userStatisticsToUpdate = userStatisticsUtils.buildDomainFromUpdateRequestDto(userStatisticsUpdateRequestDto);
            UserStatistics updatedUserStatistics = userStatisticsService.update(userStatisticsToUpdate);
            return new ResponseEntity<>(updatedUserStatistics, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserStatistics(@PathVariable("id") Long id) {
        log.info("Delete operation in /user-statistics/{}", id);
        try {
            UserStatistics userStatistics = userStatisticsService.removeById(id);
            return new ResponseEntity<>(userStatistics, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    @GetMapping("/status")
    public ResponseEntity<Object> getPossibleStatus() {
        log.info("Get operation in /user-statistics/status");
        try {
            List<UserStatisticsStatusConstants> possibleStatus = userStatisticsService.getPossibleStatus();
            return new ResponseEntity<>(possibleStatus, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion Use Cases
}
