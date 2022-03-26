package com.orchestrator.orchestrator.expose;

import com.orchestrator.orchestrator.business.AchievementService;
import com.orchestrator.orchestrator.model.Achievement;
import com.orchestrator.orchestrator.model.dto.achievement.request.AchievementChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.achievement.request.AchievementCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.achievement.request.AchievementUpdateRequestDto;
import com.orchestrator.orchestrator.utils.AchievementUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/achievement")
public class AchievementController {
    private final AchievementService achievementService;
    private final AchievementUtils achievementUtils;

    // region CRUD Operations
    @PostMapping
    public ResponseEntity<Object> createAchievement(@RequestBody AchievementCreateRequestDto achievementCreateRequestDto) {
        log.info("Post operation in /achievement");
        try {
            Achievement achievementToSave = achievementUtils.buildDomainFromCreateRequestDto(achievementCreateRequestDto);
            Achievement savedAchievement = achievementService.create(achievementToSave);
            return new ResponseEntity<>(savedAchievement, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findAchievementById(@PathVariable("id") Long id) {
        log.info("Get operation in /achievement/{}", id);
        try {
            Achievement retrievedAchievement = achievementService.findById(id);
            if (retrievedAchievement != null) {
                return new ResponseEntity<>(retrievedAchievement, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Achievement not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllAchievements(){
        log.info("Get operation in /achievement");
        try {
            List<Achievement> retrievedAchievements = achievementService.findAll();
            if (!retrievedAchievements.isEmpty()) {
                return new ResponseEntity<>(retrievedAchievements, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Achievements not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> changeAchievement(@RequestBody AchievementChangeRequestDto achievementChangeRequestDto) {
        log.info("Put operation in /achievement");
        try {
            Achievement achievementToChange = achievementUtils.buildDomainFromChangeRequestDto(achievementChangeRequestDto);
            Achievement changedAchievement = achievementService.change(achievementToChange);
            return new ResponseEntity<>(changedAchievement, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateAchievement(@RequestBody AchievementUpdateRequestDto achievementUpdateRequestDto) {
        log.info("Patch operation in /achievement");
        try {
            Achievement achievementToUpdate = achievementUtils.buildDomainFromUpdateRequestDto(achievementUpdateRequestDto);
            Achievement updatedAchievement = achievementService.update(achievementToUpdate);
            return new ResponseEntity<>(updatedAchievement, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAchievement(@PathVariable("id") Long id) {
        log.info("Delete operation in /achievement/{}", id);
        try {
            Achievement achievement = achievementService.removeById(id);
            return new ResponseEntity<>(achievement, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
