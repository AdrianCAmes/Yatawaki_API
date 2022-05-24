package com.orchestrator.orchestrator.business;

import com.orchestrator.orchestrator.model.UserRank;
import com.orchestrator.orchestrator.utils.constants.UserRankStatusConstants;

import java.util.List;

public interface UserRankService extends BaseService<UserRank, Long> {
    List<UserRankStatusConstants> getPossibleStatus();
}
