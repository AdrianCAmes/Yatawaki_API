package com.orchestrator.orchestrator.repository;

import com.orchestrator.orchestrator.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    String QUERY_QUESTIONS_RANK = "SELECT q FROM Question q WHERE q.rank.level = :level and q.status = 1";

    @Query(value = QUERY_QUESTIONS_RANK)
    List<Question> findByLevelLessOrEqualThan(Integer level);
}
