package com.orchestrator.orchestrator.repository;

import com.orchestrator.orchestrator.model.Trivia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TriviaRepository extends JpaRepository<Trivia, Long> {
}
