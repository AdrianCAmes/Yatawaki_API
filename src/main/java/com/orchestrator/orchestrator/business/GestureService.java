package com.orchestrator.orchestrator.business;

import com.orchestrator.orchestrator.model.Gesture;
import com.orchestrator.orchestrator.utils.constants.GestureStatusConstants;

import java.util.List;

public interface GestureService extends BaseService<Gesture, Long> {
    List<GestureStatusConstants> getPossibleStatus();
}
