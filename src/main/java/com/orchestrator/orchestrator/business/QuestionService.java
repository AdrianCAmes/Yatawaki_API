package com.orchestrator.orchestrator.business;

import com.orchestrator.orchestrator.model.Question;

import java.util.List;

public interface QuestionService extends BaseService<Question, Long> {
    List<Question> findByLevelLessOrEqualThan(Integer level);
}
