package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.TriviaService;
import com.orchestrator.orchestrator.model.Trivia;
import com.orchestrator.orchestrator.repository.TriviaRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TriviaServiceImpl implements TriviaService {
    private final TriviaRepository triviaRepository;
    private final GeneralUtils generalUtils;

    // region CRUD Operations
    @Override
    public Trivia create(Trivia trivia) {
        if (trivia.getIdGame() != null) throw new IllegalArgumentException("Body should not contain id");
        return triviaRepository.save(trivia);
    }

    @Override
    public Trivia findById(Long id) {
        return triviaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Trivia> findAll() {
        return triviaRepository.findAll();
    }

    @Override
    public Trivia change(Trivia trivia) {
        Trivia triviaToChange = findById(trivia.getIdGame());
        if (triviaToChange != null) {
            return triviaRepository.save(trivia);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Trivia update(Trivia trivia) throws IllegalAccessException {
        Trivia triviaToUpdate = findById(trivia.getIdGame());
        if (triviaToUpdate != null) {
            generalUtils.mapFields(trivia, triviaToUpdate);
            return triviaRepository.save(triviaToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Trivia removeById(Long id) {
        Trivia triviaToDelete = findById(id);
        if (triviaToDelete != null) {
            triviaRepository.deleteById(id);
            return triviaToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
