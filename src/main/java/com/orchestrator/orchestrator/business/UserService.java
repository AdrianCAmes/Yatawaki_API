package com.orchestrator.orchestrator.business;

import com.orchestrator.orchestrator.model.User;
import com.orchestrator.orchestrator.model.dto.user.request.UserAuthenticateRequestDto;
import com.orchestrator.orchestrator.model.dto.user.response.UserAuthenticateResponseDto;

public interface UserService extends BaseService<User, Long> {
    User register(User user) throws IllegalAccessException;
    UserAuthenticateResponseDto authenticate(UserAuthenticateRequestDto userAuthenticateRequestDto);
}
