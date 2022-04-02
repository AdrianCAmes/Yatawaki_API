package com.orchestrator.orchestrator.business;

import com.orchestrator.orchestrator.model.User;
import com.orchestrator.orchestrator.model.dto.user.response.UserResumeResponseDto;

public interface UserService extends BaseService<User, Long> {
    User register(User user) throws IllegalAccessException;
    UserResumeResponseDto findUserResumeByUsername(String username);
}
