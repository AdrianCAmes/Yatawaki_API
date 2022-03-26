package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.InstrumentService;
import com.orchestrator.orchestrator.model.Instrument;
import com.orchestrator.orchestrator.repository.InstrumentRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class InstrumentServiceImpl implements InstrumentService {
    private final InstrumentRepository instrumentRepository;
    private final GeneralUtils generalUtils;

    // region CRUD Operations
    @Override
    public Instrument create(Instrument instrument) {
        if (instrument.getIdInstrument() != null) throw new IllegalArgumentException("Body should not contain id");
        return instrumentRepository.save(instrument);
    }

    @Override
    public Instrument findById(Long id) {
        return instrumentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Instrument> findAll() {
        return instrumentRepository.findAll();
    }

    @Override
    public Instrument change(Instrument instrument) {
        Instrument instrumentToChange = findById(instrument.getIdInstrument());
        if (instrumentToChange != null) {
            return instrumentRepository.save(instrument);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Instrument update(Instrument instrument) throws IllegalAccessException {
        Instrument instrumentToUpdate = findById(instrument.getIdInstrument());
        if (instrumentToUpdate != null) {
            generalUtils.mapFields(instrument, instrumentToUpdate);
            return instrumentRepository.save(instrumentToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Instrument removeById(Long id) {
        Instrument instrumentToDelete = findById(id);
        if (instrumentToDelete != null) {
            instrumentRepository.deleteById(id);
            return instrumentToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
