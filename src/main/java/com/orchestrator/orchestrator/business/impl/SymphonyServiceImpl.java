package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.SymphonyService;
import com.orchestrator.orchestrator.model.Symphony;
import com.orchestrator.orchestrator.repository.SymphonyRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SymphonyServiceImpl implements SymphonyService {
    // Self repository
    private final SymphonyRepository symphonyRepository;
    // Utils
    private final GeneralUtils generalUtils;

    // region CRUD Operations
    @Override
    public Symphony create(Symphony symphony) {
        if (symphony.getIdUnlockable() != null) throw new IllegalArgumentException("Body should not contain id");
        return symphonyRepository.save(symphony);
    }

    @Override
    public Symphony findById(Long id) {
        return symphonyRepository.findById(id).orElse(null);
    }

    @Override
    public List<Symphony> findAll() {
        return symphonyRepository.findAll();
    }

    @Override
    public Symphony change(Symphony symphony) {
        Symphony symphonyToChange = findById(symphony.getIdUnlockable());
        if (symphonyToChange != null) {
            return symphonyRepository.save(symphony);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Symphony update(Symphony symphony) throws IllegalAccessException {
        Symphony symphonyToUpdate = findById(symphony.getIdUnlockable());
        if (symphonyToUpdate != null) {
            generalUtils.mapFields(symphony, symphonyToUpdate);
            return symphonyRepository.save(symphonyToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Symphony removeById(Long id) {
        Symphony symphonyToDelete = findById(id);
        if (symphonyToDelete != null) {
            symphonyRepository.deleteById(id);
            return symphonyToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
