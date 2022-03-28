package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.UserStatisticsService;
import com.orchestrator.orchestrator.model.UserStatistics;
import com.orchestrator.orchestrator.repository.UserStatisticsRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserStatisticsServiceImpl implements UserStatisticsService {
    // Self repository
    private final UserStatisticsRepository userStatisticsRepository;
    // Utils
    private final GeneralUtils generalUtils;

    // region CRUD Operations
    @Override
    public UserStatistics create(UserStatistics userStatistics) {
        if (userStatistics.getIdUserStatistics() != null) throw new IllegalArgumentException("Body should not contain id");
        return userStatisticsRepository.save(userStatistics);
    }

    @Override
    public UserStatistics findById(Long id) {
        return userStatisticsRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserStatistics> findAll() {
        return userStatisticsRepository.findAll();
    }

    @Override
    public UserStatistics change(UserStatistics userStatistics) {
        UserStatistics userStatisticsToChange = findById(userStatistics.getIdUserStatistics());
        if (userStatisticsToChange != null) {
            return userStatisticsRepository.save(userStatistics);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public UserStatistics update(UserStatistics userStatistics) throws IllegalAccessException {
        UserStatistics userStatisticsToUpdate = findById(userStatistics.getIdUserStatistics());
        if (userStatisticsToUpdate != null) {
            generalUtils.mapFields(userStatistics, userStatisticsToUpdate);
            return userStatisticsRepository.save(userStatisticsToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public UserStatistics removeById(Long id) {
        UserStatistics userStatisticsToDelete = findById(id);
        if (userStatisticsToDelete != null) {
            userStatisticsRepository.deleteById(id);
            return userStatisticsToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases External
    // endregion Use Cases External

    // region Use Cases Internal
    // endregion Use Cases Internal
}
