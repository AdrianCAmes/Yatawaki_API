package com.orchestrator.orchestrator.repository;

import com.orchestrator.orchestrator.model.TriviaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TriviaUserRepository extends JpaRepository<TriviaUser, Long> {
}
