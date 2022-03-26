package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.UserUnlockableService;
import com.orchestrator.orchestrator.model.UserUnlockable;
import com.orchestrator.orchestrator.repository.UserUnlockableRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserUnlockableServiceImpl implements UserUnlockableService {
    private final UserUnlockableRepository userUnlockableRepository;
    private final GeneralUtils generalUtils;

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
    // endregion Use Cases
}
