package com.orchestrator.orchestrator.repository;

import com.orchestrator.orchestrator.model.Composer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComposerRepository extends JpaRepository<Composer, Long> {
}
