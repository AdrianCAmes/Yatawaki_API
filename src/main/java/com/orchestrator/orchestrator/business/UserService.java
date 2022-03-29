package com.orchestrator.orchestrator.business;

import com.orchestrator.orchestrator.model.User;

public interface UserService extends BaseService<User, Long> {
    User register(User user) throws IllegalAccessException;
}
