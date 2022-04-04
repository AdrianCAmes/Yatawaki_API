package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.UserUnlockableService;
import com.orchestrator.orchestrator.model.Unlockable;
import com.orchestrator.orchestrator.model.UserUnlockable;
import com.orchestrator.orchestrator.model.dto.userunlockable.request.UserUnlockableCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.userunlockable.request.UserUnlockableUnlockRequestDto;
import com.orchestrator.orchestrator.repository.UnlockableRepository;
import com.orchestrator.orchestrator.repository.UserUnlockableRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.UserUnlockableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserUnlockableServiceImpl implements UserUnlockableService {
    // Self repository
    private final UserUnlockableRepository userUnlockableRepository;
    // Utils
    private final GeneralUtils generalUtils;
    private final UserUnlockableUtils userUnlockableUtils;
    // Other repositories
    private final UnlockableRepository unlockableRepository;

    // region CRUD Operations
    @Override
    public UserUnlockable create(UserUnlockable userUnlockable) {
        if (userUnlockable.getIdUserUnlockable() != null) throw new IllegalArgumentException("Body should not contain id");
        return userUnlockableRepository.save(userUnlockable);
    }

    @Override
    public UserUnlockable findById(Long id) {
        return userUnlockableRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserUnlockable> findAll() {
        return userUnlockableRepository.findAll();
    }

    @Override
    public UserUnlockable change(UserUnlockable userUnlockable) {
        UserUnlockable userUnlockableToChange = findById(userUnlockable.getIdUserUnlockable());
        if (userUnlockableToChange != null) {
            return userUnlockableRepository.save(userUnlockable);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public UserUnlockable update(UserUnlockable userUnlockable) throws IllegalAccessException {
        UserUnlockable userUnlockableToUpdate = findById(userUnlockable.getIdUserUnlockable());
        if (userUnlockableToUpdate != null) {
            generalUtils.mapFields(userUnlockable, userUnlockableToUpdate);
            return userUnlockableRepository.save(userUnlockableToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public UserUnlockable removeById(Long id) {
        UserUnlockable userUnlockableToDelete = findById(id);
        if (userUnlockableToDelete != null) {
            userUnlockableRepository.deleteById(id);
            return userUnlockableToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    @Override
    public List<Unlockable> findSymphoniesByUser(Long idUser) {
        return userUnlockableRepository.findSymphoniesByUser(idUser);
    }

    @Override
    public List<Unlockable> findAvatarsByUser(Long idUser) {
        return userUnlockableRepository.findAvatarsByUser(idUser);
    }

    @Override
    public List<Unlockable> findAchievementsByUser(Long idUser) {
        return userUnlockableRepository.findAchievementsByUser(idUser);
    }

    @Override
    public List<Unlockable> unlockObjectsByUnlocker(UserUnlockableUnlockRequestDto userUnlockableUnlockRequestDto) throws IllegalAccessException {
        List<Long> ownedUnlockables = userUnlockableRepository.findUnlockablesByUser(userUnlockableUnlockRequestDto.getIdUser()).stream().map(Unlockable::getIdUnlockable).collect(Collectors.toList());
        List<Unlockable> objectsToUnlock = unlockableRepository.findByUnlockerTypeAndUnlockerValue(userUnlockableUnlockRequestDto.getUnlockerType(), userUnlockableUnlockRequestDto.getUnlockerValue()).stream()
                .filter(object -> !ownedUnlockables.contains(object.getIdUnlockable()))
                .collect(Collectors.toList());
        if (!objectsToUnlock.isEmpty()) {
            for (Unlockable unlockable : objectsToUnlock) {
                UserUnlockableCreateRequestDto userUnlockableCreateRequestDto = new UserUnlockableCreateRequestDto();
                userUnlockableCreateRequestDto.setIdUnlockable(unlockable.getIdUnlockable());
                userUnlockableCreateRequestDto.setIdUser(userUnlockableUnlockRequestDto.getIdUser());

                UserUnlockable userUnlockable = userUnlockableUtils.buildDomainFromCreateRequestDto(userUnlockableCreateRequestDto);
                create(userUnlockable);
            }
        }
        return objectsToUnlock;
    }
    // endregion Use Cases
}
