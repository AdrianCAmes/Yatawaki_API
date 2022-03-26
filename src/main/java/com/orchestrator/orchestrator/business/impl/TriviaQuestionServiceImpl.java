package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.TriviaQuestionService;
import com.orchestrator.orchestrator.model.TriviaQuestion;
import com.orchestrator.orchestrator.repository.TriviaQuestionRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TriviaQuestionServiceImpl implements TriviaQuestionService {
    private final TriviaQuestionRepository triviaQuestionRepository;
    private final GeneralUtils generalUtils;

    // region CRUD Operations
    @Override
    public TriviaQuestion create(TriviaQuestion triviaQuestion) {
        if (triviaQuestion.getIdTriviaQuestion() != null) throw new IllegalArgumentException("Body should not contain id");
        return triviaQuestionRepository.save(triviaQuestion);
    }

    @Override
    public TriviaQuestion findById(Long id) {
        return triviaQuestionRepository.findById(id).orElse(null);
    }

    @Override
    public List<TriviaQuestion> findAll() {
        return triviaQuestionRepository.findAll();
    }

    @Override
    public TriviaQuestion change(TriviaQuestion triviaQuestion) {
        TriviaQuestion triviaQuestionToChange = findById(triviaQuestion.getIdTriviaQuestion());
        if (triviaQuestionToChange != null) {
            return triviaQuestionRepository.save(triviaQuestion);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public TriviaQuestion update(TriviaQuestion triviaQuestion) throws IllegalAccessException {
        TriviaQuestion triviaQuestionToUpdate = findById(triviaQuestion.getIdTriviaQuestion());
        if (triviaQuestionToUpdate != null) {
            generalUtils.mapFields(triviaQuestion, triviaQuestionToUpdate);
            return triviaQuestionRepository.save(triviaQuestionToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public TriviaQuestion removeById(Long id) {
        TriviaQuestion triviaQuestionToDelete = findById(id);
        if (triviaQuestionToDelete != null) {
            triviaQuestionRepository.deleteById(id);
            return triviaQuestionToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
