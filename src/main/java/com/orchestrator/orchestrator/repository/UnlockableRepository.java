package com.orchestrator.orchestrator.repository;

import com.orchestrator.orchestrator.model.Unlockable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnlockableRepository extends JpaRepository<Unlockable, Long> {
    String QUERY_TO_UNLOCK = "SELECT u FROM Unlockable u WHERE u.unlockerType = :unlockerType and u.unlockerValue <= :unlockerValue and u.status = 1";

    @Query(value = QUERY_TO_UNLOCK)
    List<Unlockable> findByUnlockerTypeAndUnlockerValue(String unlockerType, Integer unlockerValue);
}
