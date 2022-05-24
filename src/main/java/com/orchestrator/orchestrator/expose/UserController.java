package com.orchestrator.orchestrator.expose;

import com.orchestrator.orchestrator.business.UserService;
import com.orchestrator.orchestrator.model.User;
import com.orchestrator.orchestrator.model.dto.user.request.*;
import com.orchestrator.orchestrator.model.dto.user.response.UserProfileResponseDto;
import com.orchestrator.orchestrator.model.dto.user.response.UserResumeResponseDto;
import com.orchestrator.orchestrator.utils.UserUtils;
import com.orchestrator.orchestrator.utils.constants.UserRoleConstants;
import com.orchestrator.orchestrator.utils.constants.UserStatusConstants;
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
@RequestMapping("/api/v1/user")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {
    private final UserService userService;
    private final UserUtils userUtils;

    // region CRUD Operations
    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserCreateRequestDto userCreateRequestDto) {
        log.info("Post operation in /user");
        try {
            User userToSave = userUtils.buildDomainFromCreateRequestDto(userCreateRequestDto);
            User savedUser = userService.create(userToSave);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PLAYER')")
    public ResponseEntity<Object> findUserById(@PathVariable("id") Long id) {
        log.info("Get operation in /user/{}", id);
        try {
            User retrievedUser = userService.findById(id);
            if (retrievedUser != null) {
                return new ResponseEntity<>(retrievedUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllUsers(){
        log.info("Get operation in /user");
        try {
            List<User> retrievedUsers = userService.findAll();
            if (!retrievedUsers.isEmpty()) {
                return new ResponseEntity<>(retrievedUsers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Users not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PLAYER')")
    public ResponseEntity<Object> changeUser(@RequestBody UserChangeRequestDto userChangeRequestDto) {
        log.info("Put operation in /user");
        try {
            User userToChange = userUtils.buildDomainFromChangeRequestDto(userChangeRequestDto);
            User changedUser = userService.change(userToChange);
            return new ResponseEntity<>(changedUser, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<Object> updateUser(@RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        log.info("Patch operation in /user");
        try {
            User userToUpdate = userUtils.buildDomainFromUpdateRequestDto(userUpdateRequestDto);
            User updatedUser = userService.update(userToUpdate);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
        log.info("Delete operation in /user/{}", id);
        try {
            User user = userService.removeById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Object> register(@RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        log.info("Post operation in /user/register");
        try {
            User userToRegister = userUtils.buildDomainFromRegisterRequestDto(userRegisterRequestDto);
            User registeredUser = userService.register(userToRegister);
            return new ResponseEntity<>(registeredUser, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{username}/resume")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PLAYER')")
    public ResponseEntity<Object> findUserResumeByUsername(@PathVariable("username") String username) {
        log.info("Get operation in /user/{}/resume", username);
        try {
            UserResumeResponseDto userResume = userService.findUserResumeByUsername(username);
            return new ResponseEntity<>(userResume, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}/profile")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PLAYER')")
    public ResponseEntity<Object> findUserProfileById(@PathVariable("id") Long id) {
        log.info("Get operation in /user/{}/profile", id);
        try {
            UserProfileResponseDto userProfile = userService.findUserProfileByUserId(id);
            return new ResponseEntity<>(userProfile, HttpStatus.OK);
        } catch (IllegalAccessException iae) {
            return new ResponseEntity<>("Error occurred during fields mapping: " + iae.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status")
    public ResponseEntity<Object> getPossibleStatus() {
        log.info("Get operation in /user/status");
        try {
            List<UserStatusConstants> possibleStatus = userService.getPossibleStatus();
            return new ResponseEntity<>(possibleStatus, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/roles")
    public ResponseEntity<Object> getPossibleRoles() {
        log.info("Get operation in /user/roles");
        try {
            List<UserRoleConstants> possibleRoles = userService.getPossibleRoles();
            return new ResponseEntity<>(possibleRoles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during operation: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // endregion Use Cases
}
