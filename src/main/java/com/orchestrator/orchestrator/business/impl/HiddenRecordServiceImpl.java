package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.HiddenRecordService;
import com.orchestrator.orchestrator.model.HiddenRecord;
import com.orchestrator.orchestrator.repository.HiddenRecordRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class HiddenRecordServiceImpl implements HiddenRecordService {
    private final HiddenRecordRepository hiddenRecordRepository;
    private final GeneralUtils generalUtils;

    // region CRUD Operations
    @Override
    public HiddenRecord create(HiddenRecord hiddenRecord) {
        if (hiddenRecord.getIdUnlockable() != null) throw new IllegalArgumentException("Body should not contain id");
        return hiddenRecordRepository.save(hiddenRecord);
    }

    @Override
    public HiddenRecord findById(Long id) {
        return hiddenRecordRepository.findById(id).orElse(null);
    }

    @Override
    public List<HiddenRecord> findAll() {
        return hiddenRecordRepository.findAll();
    }

    @Override
    public HiddenRecord change(HiddenRecord hiddenRecord) {
        HiddenRecord hiddenRecordToChange = findById(hiddenRecord.getIdUnlockable());
        if (hiddenRecordToChange != null) {
            return hiddenRecordRepository.save(hiddenRecord);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public HiddenRecord update(HiddenRecord hiddenRecord) throws IllegalAccessException {
        HiddenRecord hiddenRecordToUpdate = findById(hiddenRecord.getIdUnlockable());
        if (hiddenRecordToUpdate != null) {
            generalUtils.mapFields(hiddenRecord, hiddenRecordToUpdate);
            return hiddenRecordRepository.save(hiddenRecordToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public HiddenRecord removeById(Long id) {
        HiddenRecord hiddenRecordToDelete = findById(id);
        if (hiddenRecordToDelete != null) {
            hiddenRecordRepository.deleteById(id);
            return hiddenRecordToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
