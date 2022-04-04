package com.orchestrator.orchestrator.expose;

import com.orchestrator.orchestrator.business.UserUnlockableService;
import com.orchestrator.orchestrator.model.Unlockable;
import com.orchestrator.orchestrator.model.UserUnlockable;
import com.orchestrator.orchestrator.model.dto.userunlockable.request.UserUnlockableChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.userunlockable.request.UserUnlockableCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.userunlockable.request.UserUnlockableUnlockRequestDto;
import com.orchestrator.orchestrator.model.dto.userunlockable.request.UserUnlockableUpdateRequestDto;
import com.orchestrator.orchestrator.model.dto.userunlockable.response.UserUnlockableFilteredResponseDto;
import com.orchestrator.orchestrator.utils.UserUnlockableUtils;
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
@RequestMapping("/api/v1/user-unlockable")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserUnlockableController {
    private final UserUnlockableService userUnlockableService;
    private final UserUnlockableUtils userUnlockableUtils;

    // region CRUD Operations
    @PostMapping
    public ResponseEntity<Object> createUserUnlockable(@RequestBody UserUnlockableCreateRequestDto userUnlockableCreateRequestDto) {
        log.info("Post operation in /user-unlockable");
        try {
            UserUnlockable userUnlockableToSave = userUnlockableUtils.buildDomainFromCreateRequestDto(userUnlockableCreateRequestDto);
            UserUnlockable savedUserUnlockable = userUnlockableService.create(userUnlockableToSave);
            return new ResponseEntity<>(savedUserUnlockable, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findUserUnlockableById(@PathVariable("id") Long id) {
        log.info("Get operation in /user-unlockable/{}", id);
        try {
            UserUnlockable retrievedUserUnlockable = userUnlockableService.findById(id);
            if (retrievedUserUnlockable != null) {
                return new ResponseEntity<>(retrievedUserUnlockable, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User Unlockable not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllUserUnlockables(){
        log.info("Get operation in /user-unlockable");
        try {
            List<UserUnlockable> retrievedUserUnlockables = userUnlockableService.findAll();
            if (!retrievedUserUnlockables.isEmpty()) {
                return new ResponseEntity<>(retrievedUserUnlockables, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User Unlockables not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> changeUserUnlockable(@RequestBody UserUnlockableChangeRequestDto userUnlockableChangeRequestDto) {
        log.info("Put operation in /user-unlockable");
        try {
            UserUnlockable userUnlockableToChange = userUnlockableUtils.buildDomainFromChangeRequestDto(userUnlockableChangeRequestDto);
            UserUnlockable changedUserUnlockable = userUnlockableService.change(userUnlockableToChange);
            return new ResponseEntity<>(changedUserUnlockable, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateUserUnlockable(@RequestBody UserUnlockableUpdateRequestDto userUnlockableUpdateRequestDto) {
        log.info("Patch operation in /user-unlockable");
        try {
            UserUnlockable userUnlockableToUpdate = userUnlockableUtils.buildDomainFromUpdateRequestDto(userUnlockableUpdateRequestDto);
            UserUnlockable updatedUserUnlockable = userUnlockableService.update(userUnlockableToUpdate);
            return new ResponseEntity<>(updatedUserUnlockable, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserUnlockable(@PathVariable("id") Long id) {
        log.info("Delete operation in /user-unlockable/{}", id);
        try {
            UserUnlockable userUnlockable = userUnlockableService.removeById(id);
            return new ResponseEntity<>(userUnlockable, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    @GetMapping("/user/{userId}/filtered")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PLAYER')")
    public ResponseEntity<Object> findFilteredUnlockablesByUserId(@PathVariable("userId") Long userId) {
        log.info("Get operation in /user-unlockable/user/{}/filtered", userId);
        try {
            UserUnlockableFilteredResponseDto retrievedUserUnlockables = userUnlockableService.findFilteredUnlockablesByUserId(userId);
            return new ResponseEntity<>(retrievedUserUnlockables, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}/avatars")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PLAYER')")
    public ResponseEntity<Object> findAvatarsByUserId(@PathVariable("userId") Long userId) {
        log.info("Get operation in /user-unlockable/user/{}/avatars", userId);
        try {
            List<Unlockable> retrievedUserUnlockables = userUnlockableService.findAvatarsByUserId(userId);
            return new ResponseEntity<>(retrievedUserUnlockables, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/unlock")
    public ResponseEntity<Object> unlockObjects(@RequestBody UserUnlockableUnlockRequestDto userUnlockableUnlockRequestDto) {
        log.info("Post operation in /user-unlockable/unlock");
        try {
            List<Unlockable> unlockedObjects = userUnlockableService.unlockObjectsByUnlocker(userUnlockableUnlockRequestDto);
            return new ResponseEntity<>(unlockedObjects, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion Use Cases
}
