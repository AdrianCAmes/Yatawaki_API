package com.orchestrator.orchestrator.expose;

import com.orchestrator.orchestrator.business.RankService;
import com.orchestrator.orchestrator.model.Rank;
import com.orchestrator.orchestrator.model.dto.rank.request.RankChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.rank.request.RankCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.rank.request.RankUpdateRequestDto;
import com.orchestrator.orchestrator.utils.RankUtils;
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
@RequestMapping("/api/v1/rank")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class RankController {
    private final RankService rankService;
    private final RankUtils rankUtils;

    // region CRUD Operations
    @PostMapping
    public ResponseEntity<Object> createRank(@RequestBody RankCreateRequestDto rankCreateRequestDto) {
        log.info("Post operation in /rank");
        try {
            Rank rankToSave = rankUtils.buildDomainFromCreateRequestDto(rankCreateRequestDto);
            Rank savedRank = rankService.create(rankToSave);
            return new ResponseEntity<>(savedRank, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findRankById(@PathVariable("id") Long id) {
        log.info("Get operation in /rank/{}", id);
        try {
            Rank retrievedRank = rankService.findById(id);
            if (retrievedRank != null) {
                return new ResponseEntity<>(retrievedRank, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Rank not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllRanks(){
        log.info("Get operation in /rank");
        try {
            List<Rank> retrievedRanks = rankService.findAll();
            if (!retrievedRanks.isEmpty()) {
                return new ResponseEntity<>(retrievedRanks, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Ranks not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> changeRank(@RequestBody RankChangeRequestDto rankChangeRequestDto) {
        log.info("Put operation in /rank");
        try {
            Rank rankToChange = rankUtils.buildDomainFromChangeRequestDto(rankChangeRequestDto);
            Rank changedRank = rankService.change(rankToChange);
            return new ResponseEntity<>(changedRank, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateRank(@RequestBody RankUpdateRequestDto rankUpdateRequestDto) {
        log.info("Patch operation in /rank");
        try {
            Rank rankToUpdate = rankUtils.buildDomainFromUpdateRequestDto(rankUpdateRequestDto);
            Rank updatedRank = rankService.update(rankToUpdate);
            return new ResponseEntity<>(updatedRank, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRank(@PathVariable("id") Long id) {
        log.info("Delete operation in /rank/{}", id);
        try {
            Rank rank = rankService.removeById(id);
            return new ResponseEntity<>(rank, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
