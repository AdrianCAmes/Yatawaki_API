package com.orchestrator.orchestrator.repository;

import com.orchestrator.orchestrator.model.HiddenRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HiddenRecordRepository extends JpaRepository<HiddenRecord, Long> {
}
