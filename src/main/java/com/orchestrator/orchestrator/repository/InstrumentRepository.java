package com.orchestrator.orchestrator.repository;

import com.orchestrator.orchestrator.model.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
    String QUERY_INSTRUMENTS_NAME = "SELECT i FROM Instrument i WHERE i.name LIKE :name and i.status = 1";

    @Query(value = QUERY_INSTRUMENTS_NAME)
    List<Instrument> findInstrumentsByName(String name);
}
