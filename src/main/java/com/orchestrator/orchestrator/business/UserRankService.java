package com.orchestrator.orchestrator.business;

import com.orchestrator.orchestrator.model.UserRank;
import com.orchestrator.orchestrator.model.dto.userrank.request.UserRankUpgradeRequestDto;
import com.orchestrator.orchestrator.model.dto.userrank.response.UserRankUpgradeResponseDto;

public interface UserRankService extends BaseService<UserRank, Long> {
    UserRankUpgradeResponseDto upgrade(UserRankUpgradeRequestDto userRankUpgradeRequestDto) throws IllegalAccessException;
}
