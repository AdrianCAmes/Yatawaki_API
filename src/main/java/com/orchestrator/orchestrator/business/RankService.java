package com.orchestrator.orchestrator.business;

import com.orchestrator.orchestrator.model.Rank;
import com.orchestrator.orchestrator.utils.constants.RankStatusConstants;

import java.util.List;

public interface RankService extends BaseService<Rank, Long> {
    List<RankStatusConstants> getPossibleStatus();
}
