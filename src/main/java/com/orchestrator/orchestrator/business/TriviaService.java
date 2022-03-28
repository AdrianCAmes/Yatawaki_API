package com.orchestrator.orchestrator.business;

import com.orchestrator.orchestrator.model.Trivia;
import com.orchestrator.orchestrator.model.dto.trivia.request.TriviaOpenRequestDto;

public interface TriviaService extends BaseService<Trivia, Long> {
    Trivia openTrivia(TriviaOpenRequestDto triviaOpenRequestDto) throws IllegalAccessException;
}
