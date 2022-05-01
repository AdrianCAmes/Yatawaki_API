package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.UnlockableService;
import com.orchestrator.orchestrator.model.Unlockable;
import com.orchestrator.orchestrator.repository.UnlockableRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.constants.ComposerStatusConstants;
import com.orchestrator.orchestrator.utils.constants.UnlockableRarenessConstants;
import com.orchestrator.orchestrator.utils.constants.UnlockableStatusConstants;
import com.orchestrator.orchestrator.utils.constants.UnlockerTypeConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnlockableServiceImpl implements UnlockableService {
    // Self repository
    private final UnlockableRepository unlockableRepository;
    // Utils
    private final GeneralUtils generalUtils;

    // region CRUD Operations
    @Override
    public Unlockable create(Unlockable unlockable) {
        if (unlockable.getIdUnlockable() != null) throw new IllegalArgumentException("Body should not contain id");
        return unlockableRepository.save(unlockable);
    }

    @Override
    public Unlockable findById(Long id) {
        return unlockableRepository.findById(id).orElse(null);
    }

    @Override
    public List<Unlockable> findAll() {
        return unlockableRepository.findAll();
    }

    @Override
    public Unlockable change(Unlockable unlockable) {
        Unlockable unlockableToChange = findById(unlockable.getIdUnlockable());
        if (unlockableToChange != null) {
            return unlockableRepository.save(unlockable);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Unlockable update(Unlockable unlockable) throws IllegalAccessException {
        Unlockable unlockableToUpdate = findById(unlockable.getIdUnlockable());
        if (unlockableToUpdate != null) {
            generalUtils.mapFields(unlockable, unlockableToUpdate);
            return unlockableRepository.save(unlockableToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Unlockable removeById(Long id) {
        Unlockable unlockableToDelete = findById(id);
        if (unlockableToDelete != null) {
            unlockableRepository.deleteById(id);
            return unlockableToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    @Override
    public List<UnlockableStatusConstants> getPossibleStatus() {
        return Arrays.stream(UnlockableStatusConstants.values()).collect(Collectors.toList());
    }

    @Override
    public List<UnlockerTypeConstants> getPossibleUnlockerTypes() {
        return Arrays.stream(UnlockerTypeConstants.values()).collect(Collectors.toList());
    }

    @Override
    public List<UnlockableRarenessConstants> getPossibleRareness() {
        return Arrays.stream(UnlockableRarenessConstants.values()).collect(Collectors.toList());
    }
    // endregion Use Cases
}
