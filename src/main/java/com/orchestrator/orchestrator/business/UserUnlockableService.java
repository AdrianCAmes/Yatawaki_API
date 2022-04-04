package com.orchestrator.orchestrator.business;

import com.orchestrator.orchestrator.model.Unlockable;
import com.orchestrator.orchestrator.model.UserUnlockable;
import com.orchestrator.orchestrator.model.dto.userunlockable.request.UserUnlockableUnlockRequestDto;
import com.orchestrator.orchestrator.model.dto.userunlockable.response.UserUnlockableFilteredResponseDto;

import java.util.List;

public interface UserUnlockableService extends BaseService<UserUnlockable, Long> {
    UserUnlockableFilteredResponseDto findFilteredUnlockablesByUserId(Long idUser);
    List<Unlockable> findAvatarsByUserId(Long idUser);
    List<Unlockable> unlockObjectsByUnlocker(UserUnlockableUnlockRequestDto userUnlockableUnlockRequestDto) throws IllegalAccessException;
}
