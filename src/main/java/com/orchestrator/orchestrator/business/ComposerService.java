package com.orchestrator.orchestrator.business;

import com.orchestrator.orchestrator.model.Composer;
import com.orchestrator.orchestrator.utils.constants.ComposerStatusConstants;

import java.util.List;

public interface ComposerService extends BaseService<Composer, Long> {
    List<ComposerStatusConstants> getPossibleStatus();
}
