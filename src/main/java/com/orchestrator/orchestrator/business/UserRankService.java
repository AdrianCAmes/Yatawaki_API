package com.orchestrator.orchestrator.business;

import com.orchestrator.orchestrator.model.UserRank;

public interface UserRankService extends BaseService<UserRank, Long> {
    UserRank upgrade(Long idUser) throws IllegalAccessException;
}
