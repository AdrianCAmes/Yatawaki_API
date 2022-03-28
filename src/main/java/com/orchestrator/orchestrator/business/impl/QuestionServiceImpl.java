package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.QuestionService;
import com.orchestrator.orchestrator.model.Question;
import com.orchestrator.orchestrator.repository.QuestionRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    // Self repository
    private final QuestionRepository questionRepository;
    // Utils
    private final GeneralUtils generalUtils;

    // region CRUD Operations
    @Override
    public Question create(Question question) {
        if (question.getIdQuestion() != null) throw new IllegalArgumentException("Body should not contain id");
        return questionRepository.save(question);
    }

    @Override
    public Question findById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question change(Question question) {
        Question questionToChange = findById(question.getIdQuestion());
        if (questionToChange != null) {
            return questionRepository.save(question);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Question update(Question question) throws IllegalAccessException {
        Question questionToUpdate = findById(question.getIdQuestion());
        if (questionToUpdate != null) {
            generalUtils.mapFields(question, questionToUpdate);
            return questionRepository.save(questionToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Question removeById(Long id) {
        Question questionToDelete = findById(id);
        if (questionToDelete != null) {
            questionRepository.deleteById(id);
            return questionToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases External
    // endregion Use Cases External

    // region Use Cases Internal
    @Override
    public List<Question> findByLevelLessOrEqualThan(Integer level) {
        return questionRepository.findByLevelLessOrEqualThan(level);
    }
    // endregion Use Cases Internal
}
