package com.orchestrator.orchestrator.expose;

import com.orchestrator.orchestrator.business.UserRankService;
import com.orchestrator.orchestrator.model.UserRank;
import com.orchestrator.orchestrator.model.dto.userrank.request.UserRankChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.userrank.request.UserRankCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.userrank.request.UserRankUpdateRequestDto;
import com.orchestrator.orchestrator.utils.UserRankUtils;
import com.orchestrator.orchestrator.utils.constants.UserRankStatusConstants;
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
@RequestMapping("/api/v1/user-rank")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserRankController {
    private final UserRankService userRankService;
    private final UserRankUtils userRankUtils;

    // region CRUD Operations
    @PostMapping
    public ResponseEntity<Object> createUserRank(@RequestBody UserRankCreateRequestDto userRankCreateRequestDto) {
        log.info("Post operation in /user-rank");
        try {
            UserRank userRankToSave = userRankUtils.buildDomainFromCreateRequestDto(userRankCreateRequestDto);
            UserRank savedUserRank = userRankService.create(userRankToSave);
            return new ResponseEntity<>(savedUserRank, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findUserRankById(@PathVariable("id") Long id) {
        log.info("Get operation in /user-rank/{}", id);
        try {
            UserRank retrievedUserRank = userRankService.findById(id);
            if (retrievedUserRank != null) {
                return new ResponseEntity<>(retrievedUserRank, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User Rank not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllUserRanks(){
        log.info("Get operation in /user-rank");
        try {
            List<UserRank> retrievedUserRanks = userRankService.findAll();
            if (!retrievedUserRanks.isEmpty()) {
                return new ResponseEntity<>(retrievedUserRanks, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User Ranks not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> changeUserRank(@RequestBody UserRankChangeRequestDto userRankChangeRequestDto) {
        log.info("Put operation in /user-rank");
        try {
            UserRank userRankToChange = userRankUtils.buildDomainFromChangeRequestDto(userRankChangeRequestDto);
            UserRank changedUserRank = userRankService.change(userRankToChange);
            return new ResponseEntity<>(changedUserRank, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateUserRank(@RequestBody UserRankUpdateRequestDto userRankUpdateRequestDto) {
        log.info("Patch operation in /user-rank");
        try {
            UserRank userRankToUpdate = userRankUtils.buildDomainFromUpdateRequestDto(userRankUpdateRequestDto);
            UserRank updatedUserRank = userRankService.update(userRankToUpdate);
            return new ResponseEntity<>(updatedUserRank, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserRank(@PathVariable("id") Long id) {
        log.info("Delete operation in /user-rank/{}", id);
        try {
            UserRank userRank = userRankService.removeById(id);
            return new ResponseEntity<>(userRank, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    @GetMapping("/status")
    public ResponseEntity<Object> getPossibleStatus() {
        log.info("Get operation in /user-rank/status");
        try {
            List<UserRankStatusConstants> possibleStatus = userRankService.getPossibleStatus();
            return new ResponseEntity<>(possibleStatus, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion Use Cases
}
