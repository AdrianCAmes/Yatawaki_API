package com.orchestrator.orchestrator.business;

import com.orchestrator.orchestrator.model.Trivia;
import com.orchestrator.orchestrator.model.dto.trivia.request.TriviaJoinRequestDto;
import com.orchestrator.orchestrator.model.dto.trivia.request.TriviaOpenRequestDto;
import com.orchestrator.orchestrator.model.dto.triviauser.request.TriviaUserCreateRequestDto;

import java.util.List;

public interface TriviaService extends BaseService<Trivia, Long> {
    Trivia openTrivia(TriviaOpenRequestDto triviaOpenRequestDto) throws IllegalAccessException;
    Trivia joinTrivia(TriviaJoinRequestDto triviaJoinRequestDto) throws IllegalAccessException;
    List<Trivia> findOpenedTrivias();
}
