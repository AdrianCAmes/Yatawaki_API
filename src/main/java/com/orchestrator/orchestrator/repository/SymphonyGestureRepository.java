package com.orchestrator.orchestrator.repository;

import com.orchestrator.orchestrator.model.SymphonyGesture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymphonyGestureRepository extends JpaRepository<SymphonyGesture, Long> {
}
