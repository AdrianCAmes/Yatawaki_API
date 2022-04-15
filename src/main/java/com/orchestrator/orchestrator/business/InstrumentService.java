package com.orchestrator.orchestrator.business;

import com.orchestrator.orchestrator.model.Instrument;
import com.orchestrator.orchestrator.utils.constants.InstrumentStatusConstants;

import java.util.List;

public interface InstrumentService extends BaseService<Instrument, Long> {
    List<Instrument> findInstrumentsByName(String name);
    List<InstrumentStatusConstants> getPossibleStatus();
}
