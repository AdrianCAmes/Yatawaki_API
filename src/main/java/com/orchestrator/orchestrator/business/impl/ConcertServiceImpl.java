package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.ConcertService;
import com.orchestrator.orchestrator.model.Concert;
import com.orchestrator.orchestrator.repository.ConcertRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ConcertServiceImpl implements ConcertService {
    private final ConcertRepository concertRepository;
    private final GeneralUtils generalUtils;

    // region CRUD Operations
    @Override
    public Concert create(Concert concert) {
        if (concert.getIdGame() != null) throw new IllegalArgumentException("Body should not contain id");
        return concertRepository.save(concert);
    }

    @Override
    public Concert findById(Long id) {
        return concertRepository.findById(id).orElse(null);
    }

    @Override
    public List<Concert> findAll() {
        return concertRepository.findAll();
    }

    @Override
    public Concert change(Concert concert) {
        Concert concertToChange = findById(concert.getIdGame());
        if (concertToChange != null) {
            return concertRepository.save(concert);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Concert update(Concert concert) throws IllegalAccessException {
        Concert concertToUpdate = findById(concert.getIdGame());
        if (concertToUpdate != null) {
            generalUtils.mapFields(concert, concertToUpdate);
            return concertRepository.save(concertToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Concert removeById(Long id) {
        Concert concertToDelete = findById(id);
        if (concertToDelete != null) {
            concertRepository.deleteById(id);
            return concertToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
