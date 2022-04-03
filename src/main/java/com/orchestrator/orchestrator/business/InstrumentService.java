package com.orchestrator.orchestrator.business;

import com.orchestrator.orchestrator.model.Instrument;

import java.util.List;

public interface InstrumentService extends BaseService<Instrument, Long> {
    List<Instrument> findInstrumentsByName(String name);
}
