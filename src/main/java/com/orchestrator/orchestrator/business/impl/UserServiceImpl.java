package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.UserService;
import com.orchestrator.orchestrator.model.*;
import com.orchestrator.orchestrator.model.dto.user.response.UserResumeResponseDto;
import com.orchestrator.orchestrator.model.dto.userrank.request.UserRankCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.userstatistics.request.UserStatisticsCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.userunlockable.request.UserUnlockableCreateRequestDto;
import com.orchestrator.orchestrator.repository.*;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.UserRankUtils;
import com.orchestrator.orchestrator.utils.UserStatisticsUtils;
import com.orchestrator.orchestrator.utils.UserUnlockableUtils;
import com.orchestrator.orchestrator.utils.constants.NumericConstants;
import com.orchestrator.orchestrator.utils.constants.UnlockerTypeConstants;
import com.orchestrator.orchestrator.utils.constants.UserUnlockableStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    // Self repository
    private final UserRepository userRepository;
    // Utils
    private final GeneralUtils generalUtils;
    private final UserStatisticsUtils userStatisticsUtils;
    private final UserRankUtils userRankUtils;
    private final UserUnlockableUtils userUnlockableUtils;
    // Other Repositories
    private final UserStatisticsRepository userStatisticsRepository;
    private final RankRepository rankRepository;
    private final UserRankRepository userRankRepository;
    private final UnlockableRepository unlockableRepository;
    private final UserUnlockableRepository userUnlockableRepository;
    // Security
    private final PasswordEncoder passwordEncoder;

    // region CRUD Operations
    @Override
    public User create(User user) {
        if (user.getIdUser() != null) throw new IllegalArgumentException("Body should not contain id");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public User update(User user) throws IllegalAccessException {
        User userToUpdate = findById(user.getIdUser());
        if (userToUpdate != null) {
            if (user.getPassword() != null) user.setPassword(passwordEncoder.encode(user.getPassword()));
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
    public User register(User user) throws IllegalAccessException {
        // Create new user statistics for user
        UserStatisticsCreateRequestDto userStatisticsCreateRequestDto = new UserStatisticsCreateRequestDto();
        UserStatistics userStatisticsToSave = userStatisticsUtils.buildDomainFromCreateRequestDto(userStatisticsCreateRequestDto);
        UserStatistics createdUserStatistics = userStatisticsRepository.save(userStatisticsToSave);

        // Create new user
        user.setUserStatistics(createdUserStatistics);
        User createdUser = create(user);

        // Set user rank to one
        UserRankCreateRequestDto userRankCreateRequestDto = new UserRankCreateRequestDto();
        userRankCreateRequestDto.setIdUser(createdUser.getIdUser());
        userRankCreateRequestDto.setIdRank(rankRepository.findByLevel(NumericConstants.ONE.getValue()).getIdRank());
        UserRank userRankToSave = userRankUtils.buildDomainFromCreateRequestDto(userRankCreateRequestDto);
        userRankRepository.save(userRankToSave);

        // Grant basic unlockable package
        List<Unlockable> baseUnlockables = unlockableRepository.findByUnlockerTypeAndUnlockerValue(UnlockerTypeConstants.RANK.getName(), 1);
        for(Unlockable unlockable : baseUnlockables) {
            UserUnlockableCreateRequestDto userUnlockableCreateRequestDto = new UserUnlockableCreateRequestDto();
            userUnlockableCreateRequestDto.setIdUser(createdUser.getIdUser());
            userUnlockableCreateRequestDto.setIdUnlockable(unlockable.getIdUnlockable());
            UserUnlockable userUnlockableToSave = userUnlockableUtils.buildDomainFromCreateRequestDto(userUnlockableCreateRequestDto);
            if (unlockable instanceof Avatar) {
                userUnlockableToSave.setStatus(UserUnlockableStatusConstants.IN_USE.getValue());
            }
            userUnlockableRepository.save(userUnlockableToSave);
        }
        return createdUser;
    }

    @Override
    public UserResumeResponseDto findUserResumeByUsername(String username) {
        User user = userRepository.findByUniqueIdentifier(username).orElse(null);
        if (user == null) {
            throw new NoSuchElementException("User does not exist in database");
        }

        UserRank lastActiveRank = userRankRepository.findLastActiveByUser(user.getIdUser()).orElse(null);
        if (lastActiveRank == null) {
            throw new NoSuchElementException("Active rank not found for user");
        }

        Optional<Unlockable> optionalAvatar = userUnlockableRepository.findInUseAvatarByUserId(user.getIdUser());
        if (optionalAvatar.isEmpty()) {
            throw new NoSuchElementException("Avatar not found for user");
        }

        List<Unlockable> ownedUnlockables = userUnlockableRepository.findSymphoniesByUserId(user.getIdUser());
        if (ownedUnlockables.isEmpty()) {
            throw new IndexOutOfBoundsException("User does not have symphonies");
        }

        UserResumeResponseDto userResume = new UserResumeResponseDto();
        userResume.setId(user.getIdUser());
        userResume.setCoinsOwned(user.getCoinsOwned());
        userResume.setNotesOwned(user.getNotesOwned());
        userResume.setLevel(lastActiveRank.getRank().getLevel());
        userResume.setCurrentExperience(lastActiveRank.getCurrentExperience());
        userResume.setIcon(optionalAvatar.get().getIcon());
        userResume.setSymphonies(ownedUnlockables);
        return userResume;
    }
    // endregion Use Cases
}
