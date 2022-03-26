package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.SymphonyGestureService;
import com.orchestrator.orchestrator.model.SymphonyGesture;
import com.orchestrator.orchestrator.repository.SymphonyGestureRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SymphonyGestureServiceImpl implements SymphonyGestureService {
    private final SymphonyGestureRepository symphonyGestureRepository;
    private final GeneralUtils generalUtils;

    // region CRUD Operations
    @Override
    public SymphonyGesture create(SymphonyGesture symphonyGesture) {
        if (symphonyGesture.getIdSymphonyGesture() != null) throw new IllegalArgumentException("Body should not contain id");
        return symphonyGestureRepository.save(symphonyGesture);
    }

    @Override
    public SymphonyGesture findById(Long id) {
        return symphonyGestureRepository.findById(id).orElse(null);
    }

    @Override
    public List<SymphonyGesture> findAll() {
        return symphonyGestureRepository.findAll();
    }

    @Override
    public SymphonyGesture change(SymphonyGesture symphonyGesture) {
        SymphonyGesture symphonyGestureToChange = findById(symphonyGesture.getIdSymphonyGesture());
        if (symphonyGestureToChange != null) {
            return symphonyGestureRepository.save(symphonyGesture);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public SymphonyGesture update(SymphonyGesture symphonyGesture) throws IllegalAccessException {
        SymphonyGesture symphonyGestureToUpdate = findById(symphonyGesture.getIdSymphonyGesture());
        if (symphonyGestureToUpdate != null) {
            generalUtils.mapFields(symphonyGesture, symphonyGestureToUpdate);
            return symphonyGestureRepository.save(symphonyGestureToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public SymphonyGesture removeById(Long id) {
        SymphonyGesture symphonyGestureToDelete = findById(id);
        if (symphonyGestureToDelete != null) {
            symphonyGestureRepository.deleteById(id);
            return symphonyGestureToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
