package com.orchestrator.orchestrator.business;

import com.orchestrator.orchestrator.model.Unlockable;
import com.orchestrator.orchestrator.model.UserUnlockable;
import com.orchestrator.orchestrator.model.dto.userunlockable.request.UserUnlockableUnlockRequestDto;

import java.util.List;

public interface UserUnlockableService extends BaseService<UserUnlockable, Long> {
    List<Unlockable> findSymphoniesByUser(Long idUser);
    List<Unlockable> unlockObjectsByUnlocker(UserUnlockableUnlockRequestDto userUnlockableUnlockRequestDto) throws IllegalAccessException;
}
