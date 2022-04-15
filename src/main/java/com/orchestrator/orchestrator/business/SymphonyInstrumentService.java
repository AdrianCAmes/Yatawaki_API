package com.orchestrator.orchestrator.business;

import com.orchestrator.orchestrator.model.Instrument;
import com.orchestrator.orchestrator.model.SymphonyInstrument;
import com.orchestrator.orchestrator.utils.constants.SymphonyInstrumentStatusConstants;

import java.util.List;

public interface SymphonyInstrumentService extends BaseService<SymphonyInstrument, Long> {
    List<Instrument> findInstrumentsBySymphony(Long idSymphony);
    List<SymphonyInstrumentStatusConstants> getPossibleStatus();
}
