package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.UserRankService;
import com.orchestrator.orchestrator.model.UserRank;
import com.orchestrator.orchestrator.repository.RankRepository;
import com.orchestrator.orchestrator.repository.UserRankRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.UserRankUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserRankServiceImpl implements UserRankService {
    // Self repository
    private final UserRankRepository userRankRepository;
    // Utils
    private final GeneralUtils generalUtils;

    // region CRUD Operations
    @Override
    public UserRank create(UserRank userRank) {
        if (userRank.getIdUserRank() != null) throw new IllegalArgumentException("Body should not contain id");
        return userRankRepository.save(userRank);
    }

    @Override
    public UserRank findById(Long id) {
        return userRankRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserRank> findAll() {
        return userRankRepository.findAll();
    }

    @Override
    public UserRank change(UserRank userRank) {
        UserRank userRankToChange = findById(userRank.getIdUserRank());
        if (userRankToChange != null) {
            return userRankRepository.save(userRank);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public UserRank update(UserRank userRank) throws IllegalAccessException {
        UserRank userRankToUpdate = findById(userRank.getIdUserRank());
        if (userRankToUpdate != null) {
            generalUtils.mapFields(userRank, userRankToUpdate);
            return userRankRepository.save(userRankToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public UserRank removeById(Long id) {
        UserRank userRankToDelete = findById(id);
        if (userRankToDelete != null) {
            userRankRepository.deleteById(id);
            return userRankToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // endregion Use Cases
}
