package com.orchestrator.orchestrator.repository;

import com.orchestrator.orchestrator.model.Instrument;
import com.orchestrator.orchestrator.model.SymphonyInstrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SymphonyInstrumentRepository extends JpaRepository<SymphonyInstrument, Long> {
    String QUERY_TO_SELECT_INSTRUMENTS = "SELECT s.instrument FROM SymphonyInstrument s WHERE s.symphony.idUnlockable = :idSymphony and s.status = 1 ";
    String QUERY_BY_SYMPHONY = "SELECT s FROM SymphonyInstrument s WHERE s.symphony.idUnlockable = :idSymphony and s.status = 1 ";

    @Query(value = QUERY_TO_SELECT_INSTRUMENTS)
    List<Instrument> findInstrumentsBySymphony(Long idSymphony);

    @Query(value = QUERY_BY_SYMPHONY)
    List<SymphonyInstrument> findBySymphony(Long idSymphony);
}
