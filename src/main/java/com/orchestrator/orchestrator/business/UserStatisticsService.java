package com.orchestrator.orchestrator.business;

import com.orchestrator.orchestrator.model.UserStatistics;
import com.orchestrator.orchestrator.utils.constants.UserStatisticsStatusConstants;

import java.util.List;

public interface UserStatisticsService extends BaseService<UserStatistics, Long> {
    List<UserStatisticsStatusConstants> getPossibleStatus();
}
