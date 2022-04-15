package com.orchestrator.orchestrator.business;

import com.orchestrator.orchestrator.model.SymphonyGesture;
import com.orchestrator.orchestrator.utils.constants.SymphonyGestureStatusConstants;

import java.util.List;

public interface SymphonyGestureService extends BaseService<SymphonyGesture, Long> {
    List<SymphonyGestureStatusConstants> getPossibleStatus();
}
