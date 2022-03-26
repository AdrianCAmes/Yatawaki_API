package com.orchestrator.orchestrator.business;

import com.orchestrator.orchestrator.model.Unlockable;

import java.util.List;

public interface UnlockableService extends BaseService<Unlockable, Long> {
    List<Unlockable> findByUnlockerTypeAndUnlockerValue(String unlockerType, Integer unlockerValue);
}
