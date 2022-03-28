package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.SymphonyInstrumentService;
import com.orchestrator.orchestrator.model.SymphonyInstrument;
import com.orchestrator.orchestrator.repository.SymphonyInstrumentRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SymphonyInstrumentServiceImpl implements SymphonyInstrumentService {
    // Self repository
    private final SymphonyInstrumentRepository symphonyInstrumentRepository;
    // Utils
    private final GeneralUtils generalUtils;

    // region CRUD Operations
    @Override
    public SymphonyInstrument create(SymphonyInstrument symphonyInstrument) {
        if (symphonyInstrument.getIdSymphonyInstrument() != null) throw new IllegalArgumentException("Body should not contain id");
        return symphonyInstrumentRepository.save(symphonyInstrument);
    }

    @Override
    public SymphonyInstrument findById(Long id) {
        return symphonyInstrumentRepository.findById(id).orElse(null);
    }

    @Override
    public List<SymphonyInstrument> findAll() {
        return symphonyInstrumentRepository.findAll();
    }

    @Override
    public SymphonyInstrument change(SymphonyInstrument symphonyInstrument) {
        SymphonyInstrument symphonyInstrumentToChange = findById(symphonyInstrument.getIdSymphonyInstrument());
        if (symphonyInstrumentToChange != null) {
            return symphonyInstrumentRepository.save(symphonyInstrument);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public SymphonyInstrument update(SymphonyInstrument symphonyInstrument) throws IllegalAccessException {
        SymphonyInstrument symphonyInstrumentToUpdate = findById(symphonyInstrument.getIdSymphonyInstrument());
        if (symphonyInstrumentToUpdate != null) {
            generalUtils.mapFields(symphonyInstrument, symphonyInstrumentToUpdate);
            return symphonyInstrumentRepository.save(symphonyInstrumentToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public SymphonyInstrument removeById(Long id) {
        SymphonyInstrument symphonyInstrumentToDelete = findById(id);
        if (symphonyInstrumentToDelete != null) {
            symphonyInstrumentRepository.deleteById(id);
            return symphonyInstrumentToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
