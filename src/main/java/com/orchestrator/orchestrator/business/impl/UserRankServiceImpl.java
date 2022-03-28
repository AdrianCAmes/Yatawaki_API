package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.RankService;
import com.orchestrator.orchestrator.business.UserRankService;
import com.orchestrator.orchestrator.model.UserRank;
import com.orchestrator.orchestrator.model.dto.userrank.request.UserRankCreateRequestDto;
import com.orchestrator.orchestrator.repository.UserRankRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.UserRankUtils;
import com.orchestrator.orchestrator.utils.constants.UserRankStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRankServiceImpl implements UserRankService {
    // Self repository
    private final UserRankRepository userRankRepository;
    // Utils
    private final GeneralUtils generalUtils;
    private final UserRankUtils userRankUtils;
    // Services
    private final RankService rankService;

    private final Integer MAX_LEVEL = 9;

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

    // region Use Cases External
    @Override
    public UserRank upgrade(Long idUser) throws IllegalAccessException {
        UserRank lastActiveRank = findLastActiveByUser(idUser);
        if (lastActiveRank == null) {
            throw new NoSuchElementException("Active rank not found for user");
        }

        // Check if it's the last status
        if (lastActiveRank.getRank().getLevel() < MAX_LEVEL) {
            // Finish the last active rank of the user
            lastActiveRank.setStatus(UserRankStatusConstants.FINISHED.getValue());
            update(lastActiveRank);

            // Establish the new rank for the user
            UserRankCreateRequestDto userRankCreateRequestDto = new UserRankCreateRequestDto();
            userRankCreateRequestDto.setIdUser(idUser);
            userRankCreateRequestDto.setIdRank(rankService.findByLevel(lastActiveRank.getRank().getLevel() + 1).getIdRank());
            UserRank nextUserRank = userRankUtils.buildDomainFromCreateRequestDto(userRankCreateRequestDto);
            return create(nextUserRank);
        } else {
            throw new ArrayIndexOutOfBoundsException("User has reached the higher rank");
        }
    }
    // endregion Use Cases External

    // region Use Cases Internal
    @Override
    public UserRank findLastActiveByUser(Long idUser) {
        return userRankRepository.findLastActiveByUser(idUser).orElse(null);
    }
    // endregion Use Cases Internal
}
