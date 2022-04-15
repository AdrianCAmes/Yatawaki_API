package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.GestureService;
import com.orchestrator.orchestrator.model.Gesture;
import com.orchestrator.orchestrator.repository.GestureRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.constants.ComposerStatusConstants;
import com.orchestrator.orchestrator.utils.constants.GestureStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GestureServiceImpl implements GestureService {
    // Self repository
    private final GestureRepository gestureRepository;
    // Utils
    private final GeneralUtils generalUtils;

    // region CRUD Operations
    @Override
    public Gesture create(Gesture gesture) {
        if (gesture.getIdGesture() != null) throw new IllegalArgumentException("Body should not contain id");
        return gestureRepository.save(gesture);
    }

    @Override
    public Gesture findById(Long id) {
        return gestureRepository.findById(id).orElse(null);
    }

    @Override
    public List<Gesture> findAll() {
        return gestureRepository.findAll();
    }

    @Override
    public Gesture change(Gesture gesture) {
        Gesture gestureToChange = findById(gesture.getIdGesture());
        if (gestureToChange != null) {
            return gestureRepository.save(gesture);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Gesture update(Gesture gesture) throws IllegalAccessException {
        Gesture gestureToUpdate = findById(gesture.getIdGesture());
        if (gestureToUpdate != null) {
            generalUtils.mapFields(gesture, gestureToUpdate);
            return gestureRepository.save(gestureToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Gesture removeById(Long id) {
        Gesture gestureToDelete = findById(id);
        if (gestureToDelete != null) {
            gestureRepository.deleteById(id);
            return gestureToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    @Override
    public List<GestureStatusConstants> getPossibleStatus() {
        return Arrays.stream(GestureStatusConstants.values()).collect(Collectors.toList());
    }
    // endregion Use Cases
}
