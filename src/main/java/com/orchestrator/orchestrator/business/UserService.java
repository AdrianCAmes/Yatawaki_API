package com.orchestrator.orchestrator.business;

import com.orchestrator.orchestrator.model.User;
import com.orchestrator.orchestrator.model.dto.user.response.UserProfileResponseDto;
import com.orchestrator.orchestrator.model.dto.user.response.UserResumeResponseDto;
import com.orchestrator.orchestrator.utils.constants.UserRoleConstants;
import com.orchestrator.orchestrator.utils.constants.UserStatusConstants;

import java.util.List;

public interface UserService extends BaseService<User, Long> {
    User register(User user) throws IllegalAccessException;
    UserResumeResponseDto findUserResumeByUsername(String username) throws IllegalAccessException;
    UserProfileResponseDto findUserProfileByUserId(Long id) throws IllegalAccessException;
    List<UserStatusConstants> getPossibleStatus();
    List<UserRoleConstants> getPossibleRoles();
    User updateByMail(User user) throws IllegalAccessException;
}
