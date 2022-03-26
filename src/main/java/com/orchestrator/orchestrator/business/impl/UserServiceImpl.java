package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.*;
import com.orchestrator.orchestrator.model.*;
import com.orchestrator.orchestrator.model.dto.userrank.request.UserRankCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.userstatistics.request.UserStatisticsCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.userunlockable.request.UserUnlockableCreateRequestDto;
import com.orchestrator.orchestrator.repository.UserRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.UserRankUtils;
import com.orchestrator.orchestrator.utils.UserStatisticsUtils;
import com.orchestrator.orchestrator.utils.UserUnlockableUtils;
import com.orchestrator.orchestrator.utils.constants.NumericConstants;
import com.orchestrator.orchestrator.utils.constants.RankConstants;
import com.orchestrator.orchestrator.utils.constants.UserStatusConstants;
import com.orchestrator.orchestrator.utils.constants.UnlockerTypeConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GeneralUtils generalUtils;
    private final UserStatisticsUtils userStatisticsUtils;
    private final UserRankUtils userRankUtils;
    private final UserUnlockableUtils userUnlockableUtils;

    private final UserStatisticsService userStatisticsService;
    private final RankService rankService;
    private final UserRankService userRankService;
    private final UnlockableService unlockableService;
    private final UserUnlockableService userUnlockableService;

    // region CRUD Operations
    @Override
    public User create(User user) {
        if (user.getIdUser() != null) throw new IllegalArgumentException("Body should not contain id");
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User change(User user) {
        User userToChange = findById(user.getIdUser());
        if (userToChange != null) {
            return userRepository.save(user);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public User update(User user) throws IllegalAccessException {
        User userToUpdate = findById(user.getIdUser());
        if (userToUpdate != null) {
            generalUtils.mapFields(user, userToUpdate);
            return userRepository.save(userToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public User removeById(Long id) {
        User userToDelete = findById(id);
        if (userToDelete != null) {
            userRepository.deleteById(id);
            return userToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    // TODO: Crear nuevo DTO para devolver
    public User register(User user) throws IllegalAccessException {
        // Create new user statistics for user
        UserStatisticsCreateRequestDto userStatisticsCreateRequestDto = new UserStatisticsCreateRequestDto();
        UserStatistics userStatisticsToSave = userStatisticsUtils.buildDomainFromCreateRequestDto(userStatisticsCreateRequestDto);
        UserStatistics createdUserStatistics = userStatisticsService.create(userStatisticsToSave);

        // Create new user
        user.setUserStatistics(createdUserStatistics);
        User createdUser = create(user);

        // Set user rank to one
        UserRankCreateRequestDto userRankCreateRequestDto = new UserRankCreateRequestDto();
        userRankCreateRequestDto.setIdUser(createdUser.getIdUser());
        userRankCreateRequestDto.setIdRank(rankService.findByName(RankConstants.RANK_ONE.getName()).getIdRank());
        UserRank userRankToSave = userRankUtils.buildDomainFromCreateRequestDto(userRankCreateRequestDto);
        userRankService.create(userRankToSave);

        // Grant basic unlockable package
        List<Unlockable> baseUnlockables = unlockableService.findByUnlockerTypeAndUnlockerValue(UnlockerTypeConstants.RANK.getName(), 1);
        for(Unlockable unlockable : baseUnlockables) {
            UserUnlockableCreateRequestDto userUnlockableCreateRequestDto = new UserUnlockableCreateRequestDto();
            userUnlockableCreateRequestDto.setIdUser(createdUser.getIdUser());
            userUnlockableCreateRequestDto.setIdUnlockable(unlockable.getIdUnlockable());
            UserUnlockable userUnlockableToSave = userUnlockableUtils.buildDomainFromCreateRequestDto(userUnlockableCreateRequestDto);
            userUnlockableService.create(userUnlockableToSave);
        }
        return createdUser;
    }
    // endregion Use Cases
}
