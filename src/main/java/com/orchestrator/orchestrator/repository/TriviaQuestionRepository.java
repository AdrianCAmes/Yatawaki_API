package com.orchestrator.orchestrator.repository;

import com.orchestrator.orchestrator.model.TriviaQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TriviaQuestionRepository extends JpaRepository<TriviaQuestion, Long> {
}
