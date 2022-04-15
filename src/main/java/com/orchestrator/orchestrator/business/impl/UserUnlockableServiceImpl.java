package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.UserUnlockableService;
import com.orchestrator.orchestrator.model.Unlockable;
import com.orchestrator.orchestrator.model.User;
import com.orchestrator.orchestrator.model.UserUnlockable;
import com.orchestrator.orchestrator.model.dto.userunlockable.request.UserUnlockableCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.userunlockable.request.UserUnlockableTradeRequestDto;
import com.orchestrator.orchestrator.model.dto.userunlockable.request.UserUnlockableUnlockRequestDto;
import com.orchestrator.orchestrator.model.dto.userunlockable.response.UserUnlockableFilteredResponseDto;
import com.orchestrator.orchestrator.repository.UnlockableRepository;
import com.orchestrator.orchestrator.repository.UserRepository;
import com.orchestrator.orchestrator.repository.UserUnlockableRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.UserUnlockableUtils;
import com.orchestrator.orchestrator.utils.constants.ComposerStatusConstants;
import com.orchestrator.orchestrator.utils.constants.UserUnlockableStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserUnlockableServiceImpl implements UserUnlockableService {
    // Self repository
    private final UserUnlockableRepository userUnlockableRepository;
    // Utils
    private final GeneralUtils generalUtils;
    private final UserUnlockableUtils userUnlockableUtils;
    // Other repositories
    private final UnlockableRepository unlockableRepository;
    private final UserRepository userRepository;

    // region CRUD Operations
    @Override
    public UserUnlockable create(UserUnlockable userUnlockable) {
        if (userUnlockable.getIdUserUnlockable() != null) throw new IllegalArgumentException("Body should not contain id");
        return userUnlockableRepository.save(userUnlockable);
    }

    @Override
    public UserUnlockable findById(Long id) {
        return userUnlockableRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserUnlockable> findAll() {
        return userUnlockableRepository.findAll();
    }

    @Override
    public UserUnlockable change(UserUnlockable userUnlockable) {
        UserUnlockable userUnlockableToChange = findById(userUnlockable.getIdUserUnlockable());
        if (userUnlockableToChange != null) {
            return userUnlockableRepository.save(userUnlockable);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public UserUnlockable update(UserUnlockable userUnlockable) throws IllegalAccessException {
        UserUnlockable userUnlockableToUpdate = findById(userUnlockable.getIdUserUnlockable());
        if (userUnlockableToUpdate != null) {
            generalUtils.mapFields(userUnlockable, userUnlockableToUpdate);
            return userUnlockableRepository.save(userUnlockableToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public UserUnlockable removeById(Long id) {
        UserUnlockable userUnlockableToDelete = findById(id);
        if (userUnlockableToDelete != null) {
            userUnlockableRepository.deleteById(id);
            return userUnlockableToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    @Override
    public UserUnlockableFilteredResponseDto findFilteredUnlockablesByUserId(Long idUser) {
        List<Unlockable> achievements = userUnlockableRepository.findAchievementsByUserId(idUser);
        List<Unlockable> symphonies = userUnlockableRepository.findSymphoniesByUserId(idUser);
        List<Unlockable> avatars = userUnlockableRepository.findAvatarsByUserId(idUser);

        UserUnlockableFilteredResponseDto userUnlockableFilteredResponseDto = new UserUnlockableFilteredResponseDto();
        userUnlockableFilteredResponseDto.setAchievements(achievements);
        userUnlockableFilteredResponseDto.setSymphonies(symphonies);
        userUnlockableFilteredResponseDto.setAvatars(avatars);

        return userUnlockableFilteredResponseDto;
    }

    @Override
    public List<Unlockable> findAvatarsByUserId(Long idUser) {
        return userUnlockableRepository.findAvatarsByUserId(idUser);
    }

    @Override
    public List<Unlockable> findMarketUnlockablesByUserId(Long idUser) {
        List<Long> ownedUnlockables = userUnlockableRepository.findUnlockablesByUserId(idUser).stream().map(Unlockable::getIdUnlockable).collect(Collectors.toList());
        return unlockableRepository.findMarketUnlockables().stream()
                .filter(object -> !ownedUnlockables.contains(object.getIdUnlockable()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Unlockable> unlockObjectsByUnlocker(UserUnlockableUnlockRequestDto userUnlockableUnlockRequestDto) throws IllegalAccessException {
        List<Long> ownedUnlockables = userUnlockableRepository.findUnlockablesByUserId(userUnlockableUnlockRequestDto.getIdUser()).stream().map(Unlockable::getIdUnlockable).collect(Collectors.toList());
        List<Unlockable> objectsToUnlock = unlockableRepository.findByUnlockerTypeAndUnlockerValue(userUnlockableUnlockRequestDto.getUnlockerType(), userUnlockableUnlockRequestDto.getUnlockerValue()).stream()
                .filter(object -> !ownedUnlockables.contains(object.getIdUnlockable()))
                .collect(Collectors.toList());
        if (!objectsToUnlock.isEmpty()) {
            for (Unlockable unlockable : objectsToUnlock) {
                UserUnlockableCreateRequestDto userUnlockableCreateRequestDto = new UserUnlockableCreateRequestDto();
                userUnlockableCreateRequestDto.setIdUnlockable(unlockable.getIdUnlockable());
                userUnlockableCreateRequestDto.setIdUser(userUnlockableUnlockRequestDto.getIdUser());

                UserUnlockable userUnlockable = userUnlockableUtils.buildDomainFromCreateRequestDto(userUnlockableCreateRequestDto);
                create(userUnlockable);
            }
        }
        return objectsToUnlock;
    }

    @Override
    public Unlockable tradeObject(UserUnlockableTradeRequestDto userUnlockableTradeRequestDto) throws IllegalAccessException {
        Unlockable unlockableToTrade = unlockableRepository.findById(userUnlockableTradeRequestDto.getIdUnlockable()).orElse(null);
        if (unlockableToTrade == null) {
            throw new NoSuchElementException("Unlockable not found in database");
        }
        User user = userRepository.findById(userUnlockableTradeRequestDto.getIdUser()).orElse(null);
        if (user == null) {
            throw new NoSuchElementException("User not found in database");
        }
        if (unlockableToTrade.getCoinsCost() > user.getCoinsOwned()) {
            throw new UnsupportedOperationException("User does not have sufficient coins");
        }
        List<Long> ownedUnlockables = userUnlockableRepository.findUnlockablesByUserId(user.getIdUser()).stream().map(Unlockable::getIdUnlockable).collect(Collectors.toList());
        if (ownedUnlockables.contains(unlockableToTrade.getIdUnlockable())) {
            throw new UnsupportedOperationException("Object already unlocked for user");
        }
        user.setCoinsOwned(user.getCoinsOwned() - unlockableToTrade.getCoinsCost());
        userRepository.save(user);

        UserUnlockableCreateRequestDto userUnlockableCreateRequestDto = new UserUnlockableCreateRequestDto();
        userUnlockableCreateRequestDto.setIdUnlockable(userUnlockableTradeRequestDto.getIdUnlockable());
        userUnlockableCreateRequestDto.setIdUser(userUnlockableTradeRequestDto.getIdUser());

        UserUnlockable userUnlockable = userUnlockableUtils.buildDomainFromCreateRequestDto(userUnlockableCreateRequestDto);
        create(userUnlockable);
        return unlockableToTrade;
    }

    @Override
    public List<UserUnlockableStatusConstants> getPossibleStatus() {
        return Arrays.stream(UserUnlockableStatusConstants.values()).collect(Collectors.toList());
    }
    // endregion Use Cases
}
