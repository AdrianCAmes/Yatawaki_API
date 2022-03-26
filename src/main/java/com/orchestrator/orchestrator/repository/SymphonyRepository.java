package com.orchestrator.orchestrator.repository;

import com.orchestrator.orchestrator.model.Symphony;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymphonyRepository extends JpaRepository<Symphony, Long> {
}
