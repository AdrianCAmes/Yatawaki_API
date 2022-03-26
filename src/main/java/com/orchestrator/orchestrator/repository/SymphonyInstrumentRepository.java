package com.orchestrator.orchestrator.repository;

import com.orchestrator.orchestrator.model.SymphonyInstrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymphonyInstrumentRepository extends JpaRepository<SymphonyInstrument, Long> {
}
