package com.orchestrator.orchestrator.business;

import com.orchestrator.orchestrator.model.Unlockable;
import com.orchestrator.orchestrator.utils.constants.UnlockableStatusConstants;
import com.orchestrator.orchestrator.utils.constants.UnlockerTypeConstants;

import java.util.List;

public interface UnlockableService extends BaseService<Unlockable, Long> {
    List<UnlockableStatusConstants> getPossibleStatus();
    List<UnlockerTypeConstants> getPossibleUnlockerTypes();
}
