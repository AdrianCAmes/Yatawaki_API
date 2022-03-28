package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.*;
import com.orchestrator.orchestrator.model.*;
import com.orchestrator.orchestrator.model.dto.trivia.request.TriviaCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.trivia.request.TriviaOpenRequestDto;
import com.orchestrator.orchestrator.model.dto.triviaquestion.request.TriviaQuestionCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.triviauser.request.TriviaUserCreateRequestDto;
import com.orchestrator.orchestrator.repository.TriviaRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.TriviaQuestionUtils;
import com.orchestrator.orchestrator.utils.TriviaUserUtils;
import com.orchestrator.orchestrator.utils.TriviaUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TriviaServiceImpl implements TriviaService {
    // Self repository
    private final TriviaRepository triviaRepository;
    // Utils
    private final GeneralUtils generalUtils;
    private final TriviaUtils triviaUtils;
    private final TriviaUserUtils triviaUserUtils;
    private final TriviaQuestionUtils triviaQuestionUtils;
    // Services
    private final UserService userService;
    private final UserRankService userRankService;
    private final QuestionService questionService;
    private final TriviaUserService triviaUserService;
    private final TriviaQuestionService triviaQuestionService;

    private final Integer NUMBER_OF_QUESTIONS = 4;

    // region CRUD Operations
    @Override
    public Trivia create(Trivia trivia) {
        if (trivia.getIdGame() != null) throw new IllegalArgumentException("Body should not contain id");
        return triviaRepository.save(trivia);
    }

    @Override
    public Trivia findById(Long id) {
        return triviaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Trivia> findAll() {
        return triviaRepository.findAll();
    }

    @Override
    public Trivia change(Trivia trivia) {
        Trivia triviaToChange = findById(trivia.getIdGame());
        if (triviaToChange != null) {
            return triviaRepository.save(trivia);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Trivia update(Trivia trivia) throws IllegalAccessException {
        Trivia triviaToUpdate = findById(trivia.getIdGame());
        if (triviaToUpdate != null) {
            generalUtils.mapFields(trivia, triviaToUpdate);
            return triviaRepository.save(triviaToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Trivia removeById(Long id) {
        Trivia triviaToDelete = findById(id);
        if (triviaToDelete != null) {
            triviaRepository.deleteById(id);
            return triviaToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases External
    @Override
    public Trivia openTrivia(TriviaOpenRequestDto triviaOpenRequestDto) throws IllegalAccessException {
        // Find user that created the trivia
        User creatorUser = userService.findById(triviaOpenRequestDto.getIdUser());
        UserRank activeUserRank = userRankService.findLastActiveByUser(triviaOpenRequestDto.getIdUser());
        if (creatorUser == null || activeUserRank == null) {
            throw new NoSuchElementException("Creator user not found in database");
        }

        // Find questions that match user level
        List<Question> questionsForUser = questionService.findByLevelLessOrEqualThan(activeUserRank.getRank().getLevel());
        if (questionsForUser.size() < NUMBER_OF_QUESTIONS) {
            throw  new IndexOutOfBoundsException("Not enough questions in database");
        }

        // Create new trivia
        TriviaCreateRequestDto triviaCreateRequestDto = new TriviaCreateRequestDto();
        triviaCreateRequestDto.setIdSymphony(triviaOpenRequestDto.getIdSymphony());
        triviaCreateRequestDto.setCurrencyPool(triviaCreateRequestDto.getCurrencyPool());
        triviaCreateRequestDto.setHallCode(triviaCreateRequestDto.getHallCode());
        Trivia triviaToSave = triviaUtils.buildDomainFromCreateRequestDto(triviaCreateRequestDto);
        Trivia savedTrivia = create(triviaToSave);

        // Create new trivia user
        TriviaUserCreateRequestDto triviaUserCreateRequestDto = new TriviaUserCreateRequestDto();
        triviaUserCreateRequestDto.setIdUser(triviaOpenRequestDto.getIdUser());
        triviaUserCreateRequestDto.setIdTrivia(savedTrivia.getIdGame());
        TriviaUser triviaUserToSave = triviaUserUtils.buildDomainFromCreateRequestDto(triviaUserCreateRequestDto);
        triviaUserService.create(triviaUserToSave);

        // Set new questions to trivia
        List<Integer> selectedIndexes = new ArrayList<>();
        SecureRandom secureRandom = new SecureRandom();
        for (Integer i = 0; i < NUMBER_OF_QUESTIONS; i++) {
            Integer selectedIndex;
            do {
                selectedIndex = secureRandom.nextInt(questionsForUser.size());
            } while (selectedIndexes.contains(selectedIndex));
            selectedIndexes.add(selectedIndex);
            TriviaQuestionCreateRequestDto triviaQuestionCreateRequestDto = new TriviaQuestionCreateRequestDto();
            triviaQuestionCreateRequestDto.setIdTrivia(savedTrivia.getIdGame());
            triviaQuestionCreateRequestDto.setIdQuestion(questionsForUser.get(selectedIndex).getIdQuestion());
            TriviaQuestion triviaQuestionToSave = triviaQuestionUtils.buildDomainFromCreateRequestDto(triviaQuestionCreateRequestDto);
            triviaQuestionService.create(triviaQuestionToSave);
        }
        return savedTrivia;
    }
    // endregion Use Cases External

    // region Use Cases Internal
    // endregion Use Cases Internal
}
