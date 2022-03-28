package com.orchestrator.orchestrator.repository;

import com.orchestrator.orchestrator.model.Trivia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TriviaRepository extends JpaRepository<Trivia, Long> {
    List<Trivia> findTriviasByStatus(Integer status);
}
