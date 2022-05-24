package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.ComposerService;
import com.orchestrator.orchestrator.model.Composer;
import com.orchestrator.orchestrator.repository.ComposerRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.constants.ComposerStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComposerServiceImpl implements ComposerService {
    // Self repository
    private final ComposerRepository composerRepository;
    // Utils
    private final GeneralUtils generalUtils;

    // region CRUD Operations
    @Override
    public Composer create(Composer composer) {
        if (composer.getIdComposer() != null) throw new IllegalArgumentException("Body should not contain id");
        return composerRepository.save(composer);
    }

    @Override
    public Composer findById(Long id) {
        return composerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Composer> findAll() {
        return composerRepository.findAll();
    }

    @Override
    public Composer change(Composer composer) {
        Composer composerToChange = findById(composer.getIdComposer());
        if (composerToChange != null) {
            return composerRepository.save(composer);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Composer update(Composer composer) throws IllegalAccessException {
        Composer composerToUpdate = findById(composer.getIdComposer());
        if (composerToUpdate != null) {
            generalUtils.mapFields(composer, composerToUpdate);
            return composerRepository.save(composerToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Composer removeById(Long id) {
        Composer composerToDelete = findById(id);
        if (composerToDelete != null) {
            composerRepository.deleteById(id);
            return composerToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    @Override
    public List<ComposerStatusConstants> getPossibleStatus() {
        return Arrays.stream(ComposerStatusConstants.values()).collect(Collectors.toList());
    }
    // endregion Use Cases
}
