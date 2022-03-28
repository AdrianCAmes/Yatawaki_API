package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.TriviaUserService;
import com.orchestrator.orchestrator.model.TriviaUser;
import com.orchestrator.orchestrator.repository.TriviaRepository;
import com.orchestrator.orchestrator.repository.TriviaUserRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.TriviaUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TriviaUserServiceImpl implements TriviaUserService {
    // Self repository
    private final TriviaUserRepository triviaUserRepository;
    // Utils
    private final GeneralUtils generalUtils;

    // region CRUD Operations
    @Override
    public TriviaUser create(TriviaUser triviaUser) {
        if (triviaUser.getIdTriviaUser() != null) throw new IllegalArgumentException("Body should not contain id");
        return triviaUserRepository.save(triviaUser);
    }

    @Override
    public TriviaUser findById(Long id) {
        return triviaUserRepository.findById(id).orElse(null);
    }

    @Override
    public List<TriviaUser> findAll() {
        return triviaUserRepository.findAll();
    }

    @Override
    public TriviaUser change(TriviaUser triviaUser) {
        TriviaUser triviaUserToChange = findById(triviaUser.getIdTriviaUser());
        if (triviaUserToChange != null) {
            return triviaUserRepository.save(triviaUser);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public TriviaUser update(TriviaUser triviaUser) throws IllegalAccessException {
        TriviaUser triviaUserToUpdate = findById(triviaUser.getIdTriviaUser());
        if (triviaUserToUpdate != null) {
            generalUtils.mapFields(triviaUser, triviaUserToUpdate);
            return triviaUserRepository.save(triviaUserToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public TriviaUser removeById(Long id) {
        TriviaUser triviaUserToDelete = findById(id);
        if (triviaUserToDelete != null) {
            triviaUserRepository.deleteById(id);
            return triviaUserToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
