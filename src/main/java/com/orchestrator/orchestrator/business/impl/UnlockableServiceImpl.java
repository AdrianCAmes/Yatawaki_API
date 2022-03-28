package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.UnlockableService;
import com.orchestrator.orchestrator.model.Unlockable;
import com.orchestrator.orchestrator.repository.UnlockableRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    // region Use Cases External
    // endregion Use Cases External

    // region Use Cases Internal
    @Override
    public List<Unlockable> findByUnlockerTypeAndUnlockerValue(String unlockerType, Integer unlockerValue) {
        return unlockableRepository.findByUnlockerTypeAndUnlockerValue(unlockerType, unlockerValue);
    }
    // endregion Use Cases Internal
}
