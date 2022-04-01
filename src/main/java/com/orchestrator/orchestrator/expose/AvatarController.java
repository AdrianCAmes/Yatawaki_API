package com.orchestrator.orchestrator.expose;

import com.orchestrator.orchestrator.business.AvatarService;
import com.orchestrator.orchestrator.model.Avatar;
import com.orchestrator.orchestrator.model.dto.avatar.request.AvatarChangeRequestDto;
import com.orchestrator.orchestrator.model.dto.avatar.request.AvatarCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.avatar.request.AvatarUpdateRequestDto;
import com.orchestrator.orchestrator.utils.AvatarUtils;
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
@RequestMapping("/api/v1/avatar")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AvatarController {
    private final AvatarService avatarService;
    private final AvatarUtils avatarUtils;

    // region CRUD Operations
    @PostMapping
    public ResponseEntity<Object> createAvatar(@RequestBody AvatarCreateRequestDto avatarCreateRequestDto) {
        log.info("Post operation in /avatar");
        try {
            Avatar avatarToSave = avatarUtils.buildDomainFromCreateRequestDto(avatarCreateRequestDto);
            Avatar savedAvatar = avatarService.create(avatarToSave);
            return new ResponseEntity<>(savedAvatar, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findAvatarById(@PathVariable("id") Long id) {
        log.info("Get operation in /avatar/{}", id);
        try {
            Avatar retrievedAvatar = avatarService.findById(id);
            if (retrievedAvatar != null) {
                return new ResponseEntity<>(retrievedAvatar, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Avatar not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllAvatars(){
        log.info("Get operation in /avatar");
        try {
            List<Avatar> retrievedAvatars = avatarService.findAll();
            if (!retrievedAvatars.isEmpty()) {
                return new ResponseEntity<>(retrievedAvatars, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Avatars not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> changeAvatar(@RequestBody AvatarChangeRequestDto avatarChangeRequestDto) {
        log.info("Put operation in /avatar");
        try {
            Avatar avatarToChange = avatarUtils.buildDomainFromChangeRequestDto(avatarChangeRequestDto);
            Avatar changedAvatar = avatarService.change(avatarToChange);
            return new ResponseEntity<>(changedAvatar, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<Object> updateAvatar(@RequestBody AvatarUpdateRequestDto avatarUpdateRequestDto) {
        log.info("Patch operation in /avatar");
        try {
            Avatar avatarToUpdate = avatarUtils.buildDomainFromUpdateRequestDto(avatarUpdateRequestDto);
            Avatar updatedAvatar = avatarService.update(avatarToUpdate);
            return new ResponseEntity<>(updatedAvatar, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAvatar(@PathVariable("id") Long id) {
        log.info("Delete operation in /avatar/{}", id);
        try {
            Avatar avatar = avatarService.removeById(id);
            return new ResponseEntity<>(avatar, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
