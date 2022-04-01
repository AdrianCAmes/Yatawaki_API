package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.TriviaService;
import com.orchestrator.orchestrator.model.*;
import com.orchestrator.orchestrator.model.dto.trivia.request.TriviaCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.trivia.request.TriviaJoinRequestDto;
import com.orchestrator.orchestrator.model.dto.trivia.request.TriviaOpenRequestDto;
import com.orchestrator.orchestrator.model.dto.triviaquestion.request.TriviaQuestionCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.triviauser.request.TriviaUserCreateRequestDto;
import com.orchestrator.orchestrator.repository.*;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.TriviaQuestionUtils;
import com.orchestrator.orchestrator.utils.TriviaUserUtils;
import com.orchestrator.orchestrator.utils.TriviaUtils;
import com.orchestrator.orchestrator.utils.constants.GameStatusConstants;
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
    // Other repositories
    private final UserRankRepository userRankRepository;
    private final QuestionRepository questionRepository;
    private final TriviaUserRepository triviaUserRepository;
    private final TriviaQuestionRepository triviaQuestionRepository;

    private final Integer NUMBER_OF_QUESTIONS = 4;
    private final Integer NUMBER_OF_PARTICIPANTS = 4;

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

    // region Use Cases
    @Override
    public Trivia openTrivia(TriviaOpenRequestDto triviaOpenRequestDto) throws IllegalAccessException {
        // Find rank of the user that created the trivia
        UserRank activeUserRank = userRankRepository.findLastActiveByUser(triviaOpenRequestDto.getIdUser()).orElse(null);
        if (activeUserRank == null) {
            throw new NoSuchElementException("Creator user does not have an active rank");
        }
        // Find questions that match user level
        List<Question> questionsForUser = questionRepository.findByLevelLessOrEqualThan(activeUserRank.getRank().getLevel());
        if (questionsForUser.size() < NUMBER_OF_QUESTIONS) {
            throw  new IndexOutOfBoundsException("Not enough questions in database");
        }

        // Create new trivia
        TriviaCreateRequestDto triviaCreateRequestDto = new TriviaCreateRequestDto();
        triviaCreateRequestDto.setIdSymphony(triviaOpenRequestDto.getIdSymphony());
        triviaCreateRequestDto.setNotesPool(triviaOpenRequestDto.getNotesPool());
        triviaCreateRequestDto.setHallCode(triviaOpenRequestDto.getHallCode());
        Trivia triviaToSave = triviaUtils.buildDomainFromCreateRequestDto(triviaCreateRequestDto);
        Trivia savedTrivia = create(triviaToSave);

        // Join creator to trivia
        TriviaUserCreateRequestDto triviaUserCreateRequestDto = new TriviaUserCreateRequestDto();
        triviaUserCreateRequestDto.setIdUser(triviaOpenRequestDto.getIdUser());
        triviaUserCreateRequestDto.setIdTrivia(savedTrivia.getIdGame());
        TriviaUser triviaUserToSave = triviaUserUtils.buildDomainFromCreateRequestDto(triviaUserCreateRequestDto);
        triviaUserRepository.save(triviaUserToSave);

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
            triviaQuestionRepository.save(triviaQuestionToSave);
        }
        return savedTrivia;
    }

    @Override
    public Trivia joinTrivia(TriviaJoinRequestDto triviaJoinRequestDto) throws IllegalAccessException {
        Trivia triviaToJoin = triviaRepository.findById(triviaJoinRequestDto.getIdTrivia()).orElse(null);
        if (triviaToJoin == null) {
            throw new NoSuchElementException("Trivia to join not found in database");
        }
        if (triviaToJoin.getStatus() != GameStatusConstants.WAITING.getValue()) {
            throw new IllegalStateException("Trivia is not opened anymore");
        }
        triviaToJoin.setNumberOfParticipants(triviaToJoin.getNumberOfParticipants() + 1);
        if (triviaToJoin.getNumberOfParticipants() == NUMBER_OF_PARTICIPANTS) {
            triviaToJoin.setStatus(GameStatusConstants.STARTED.getValue());
        }
        triviaRepository.save(triviaToJoin);

        TriviaUserCreateRequestDto triviaUserCreateRequestDto = new TriviaUserCreateRequestDto();
        triviaUserCreateRequestDto.setIdTrivia(triviaJoinRequestDto.getIdTrivia());
        triviaUserCreateRequestDto.setIdUser(triviaJoinRequestDto.getIdUser());
        TriviaUser triviaUserToCreate = triviaUserUtils.buildDomainFromCreateRequestDto(triviaUserCreateRequestDto);
        triviaUserRepository.save(triviaUserToCreate);
        return triviaToJoin;
    }

    @Override
    public List<Trivia> findOpenedTrivias() {
        return triviaRepository.findTriviasByStatus(GameStatusConstants.WAITING.getValue());
    }
    // endregion Use Cases
}
