package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.ConcertService;
import com.orchestrator.orchestrator.model.*;
import com.orchestrator.orchestrator.model.dto.concert.request.ConcertCompleteRequestDto;
import com.orchestrator.orchestrator.model.dto.concert.request.ConcertCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.concert.response.ConcertCompleteResponseDto;
import com.orchestrator.orchestrator.model.dto.concert.response.ConcertStartResponseDto;
import com.orchestrator.orchestrator.model.dto.instrument.response.InstrumentStartResponseDto;
import com.orchestrator.orchestrator.model.dto.userrank.request.UserRankCreateRequestDto;
import com.orchestrator.orchestrator.model.dto.userunlockable.request.UserUnlockableCreateRequestDto;
import com.orchestrator.orchestrator.repository.*;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.UserRankUtils;
import com.orchestrator.orchestrator.utils.UserUnlockableUtils;
import com.orchestrator.orchestrator.utils.constants.ComposerStatusConstants;
import com.orchestrator.orchestrator.utils.constants.ConcertStatusConstants;
import com.orchestrator.orchestrator.utils.constants.UnlockerTypeConstants;
import com.orchestrator.orchestrator.utils.constants.UserRankStatusConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConcertServiceImpl implements ConcertService {
    // Self repository
    private final ConcertRepository concertRepository;
    // Utils
    private final GeneralUtils generalUtils;
    private final UserRankUtils userRankUtils;
    private final UserUnlockableUtils userUnlockableUtils;
    // Other Repositories
    private final UserRankRepository userRankRepository;
    private final RankRepository rankRepository;
    private final UserRepository userRepository;
    private final UserUnlockableRepository userUnlockableRepository;
    private final UnlockableRepository unlockableRepository;
    private final UserStatisticsRepository userStatisticsRepository;
    private final SymphonyRepository symphonyRepository;
    private final SymphonyInstrumentRepository symphonyInstrumentRepository;

    private final Integer MAX_LEVEL = 9;

    // region CRUD Operations
    @Override
    public Concert create(Concert concert) {
        if (concert.getIdConcert() != null) throw new IllegalArgumentException("Body should not contain id");
        return concertRepository.save(concert);
    }

    @Override
    public Concert findById(Long id) {
        return concertRepository.findById(id).orElse(null);
    }

    @Override
    public List<Concert> findAll() {
        return concertRepository.findAll();
    }

    @Override
    public Concert change(Concert concert) {
        Concert concertToChange = findById(concert.getIdConcert());
        if (concertToChange != null) {
            return concertRepository.save(concert);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Concert update(Concert concert) throws IllegalAccessException {
        Concert concertToUpdate = findById(concert.getIdConcert());
        if (concertToUpdate != null) {
            generalUtils.mapFields(concert, concertToUpdate);
            return concertRepository.save(concertToUpdate);
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }

    @Override
    public Concert removeById(Long id) {
        Concert concertToDelete = findById(id);
        if (concertToDelete != null) {
            concertRepository.deleteById(id);
            return concertToDelete;
        } else {
            throw new NoSuchElementException("Element does not exist in database");
        }
    }
    // endregion CRUD Operations

    // region Use Cases
    @Override
    public ConcertCompleteResponseDto complete(ConcertCompleteRequestDto concertCompleteRequestDto) throws IllegalAccessException {
        Concert concert = findById(concertCompleteRequestDto.getIdConcert());
        if (concert == null) {
            throw new NoSuchElementException("Concert not found in database");
        }
        User user = userRepository.findById(concert.getUser().getIdUser()).orElse(null);
        if (user == null) {
            throw new NoSuchElementException("User not found in database");
        }
        UserStatistics userStatistics = userStatisticsRepository.findById(user.getUserStatistics().getIdUserStatistics()).orElse(null);
        if (userStatistics == null) {
            throw new NoSuchElementException("User Statistics not found in database");
        }
        ConcertCompleteResponseDto concertCompleteResponseDto = new ConcertCompleteResponseDto();
        updateConcertResults(concertCompleteRequestDto);
        updateUserCurrency(concertCompleteRequestDto, user);
        addExperience(concertCompleteRequestDto, concertCompleteResponseDto, user);
        addPlayedConcerts(concertCompleteRequestDto, userStatistics);
        unlockObjects(concertCompleteRequestDto, concertCompleteResponseDto, user, userStatistics);
        return concertCompleteResponseDto;
    }

    @Override
    public ConcertStartResponseDto start(Concert concert) throws IllegalAccessException {
        Concert createdConcert = create(concert);
        Symphony startSymphony = symphonyRepository.findById(createdConcert.getSymphony().getIdUnlockable()).orElse(null);
        if (startSymphony == null) {
            throw new NoSuchElementException("Symphony not found");
        }
        List<SymphonyInstrument> symphonyInstruments = symphonyInstrumentRepository.findBySymphony(createdConcert.getSymphony().getIdUnlockable());
        if (symphonyInstruments.isEmpty()) {
            throw new NoSuchElementException("Instruments not found ind symphony");
        }
        ConcertStartResponseDto concertStartResponseDto = new ConcertStartResponseDto();
        generalUtils.mapFields(startSymphony, concertStartResponseDto);
        concertStartResponseDto.setInstruments(symphonyInstruments.stream().map(symphonyInstrument -> {
            InstrumentStartResponseDto instrumentStartResponseDto = new InstrumentStartResponseDto();
            try {
                generalUtils.mapFields(symphonyInstrument.getInstrument(), instrumentStartResponseDto);
                generalUtils.mapFields(symphonyInstrument, instrumentStartResponseDto);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return instrumentStartResponseDto;
        }).collect(Collectors.toList()));
        return concertStartResponseDto;
    }

    @Override
    public List<ConcertStatusConstants> getPossibleStatus() {
        return Arrays.stream(ConcertStatusConstants.values()).collect(Collectors.toList());
    }
    // endregion Use Cases

    // region Private Functions
    private void updateConcertResults(ConcertCompleteRequestDto request) throws IllegalAccessException {
        Concert concertToUpdate = new Concert();
        concertToUpdate.setIdConcert(request.getIdConcert());
        concertToUpdate.setPoints(request.getPoints());
        concertToUpdate.setGesturesCompleted(request.getGesturesCompleted());
        concertToUpdate.setAccuracyRate(request.getAccuracyRate());
        concertToUpdate.setStatus(ConcertStatusConstants.FINISHED.getValue());
        update(concertToUpdate);
    }

    private void updateUserCurrency(ConcertCompleteRequestDto request, User user) {
        user.setCoinsOwned(user.getCoinsOwned() + request.getGainedCoins());
        userRepository.save(user);
    }

    private void addExperience(ConcertCompleteRequestDto request, ConcertCompleteResponseDto response, User user) throws IllegalAccessException {
        UserRank lastActiveRank = userRankRepository.findLastActiveByUser(user.getIdUser()).orElse(null);
        if (lastActiveRank == null) {
            throw new NoSuchElementException("Active rank not found for user");
        }

        Integer newCurrentExperience = lastActiveRank.getCurrentExperience() + request.getGainedExperience();

        if (newCurrentExperience < lastActiveRank.getRank().getMaxExperience()) {
            lastActiveRank.setCurrentExperience(newCurrentExperience);
            userRankRepository.save(lastActiveRank);
        } else {
            newCurrentExperience = newCurrentExperience - lastActiveRank.getRank().getMaxExperience();

            // Check if it's the last status
            if (lastActiveRank.getRank().getLevel() < MAX_LEVEL) {
                // Finish the last active rank of the user
                lastActiveRank.setEndDate(LocalDate.now());
                lastActiveRank.setStatus(UserRankStatusConstants.FINISHED.getValue());
                lastActiveRank.setCurrentExperience(lastActiveRank.getRank().getMaxExperience());
                userRankRepository.save(lastActiveRank);

                // Establish the new rank for the user
                UserRankCreateRequestDto userRankCreateRequestDto = new UserRankCreateRequestDto();
                userRankCreateRequestDto.setIdUser(user.getIdUser());
                userRankCreateRequestDto.setIdRank(rankRepository.findByLevel(lastActiveRank.getRank().getLevel() + 1).getIdRank());
                UserRank nextUserRank = userRankUtils.buildDomainFromCreateRequestDto(userRankCreateRequestDto);
                nextUserRank.setCurrentExperience(newCurrentExperience);
                userRankRepository.save(nextUserRank);

                Rank newRank = rankRepository.findById(nextUserRank.getRank().getIdRank()).orElse(null);
                if (newRank == null) {
                    throw new NoSuchElementException("User Rank not found in database");
                }
                response.setNewRank(newRank);
            } else if (lastActiveRank.getCurrentExperience() < lastActiveRank.getRank().getMaxExperience()) {
                lastActiveRank.setCurrentExperience(lastActiveRank.getRank().getMaxExperience());
                userRankRepository.save(lastActiveRank);
            }
        }
    }

    private void addPlayedConcerts(ConcertCompleteRequestDto concertCompleteRequestDto, UserStatistics userStatistics) {
        Integer oldConcertsOrchestrated = userStatistics.getConcertsOrchestrated();
        Double oldOrchestrationAccuracy = userStatistics.getOrchestrationAccuracy();
        Integer newConcertsOrchestrated = oldConcertsOrchestrated + 1;

        Double newOrchestrationAccuracy = ((oldOrchestrationAccuracy * oldConcertsOrchestrated) + concertCompleteRequestDto.getAccuracyRate()) / newConcertsOrchestrated;
        userStatistics.setConcertsOrchestrated(newConcertsOrchestrated);
        userStatistics.setOrchestrationAccuracy(newOrchestrationAccuracy);
        userStatisticsRepository.save(userStatistics);
    }

    private void unlockObjects(ConcertCompleteRequestDto request, ConcertCompleteResponseDto response, User user, UserStatistics userStatistics) throws IllegalAccessException {
        List<Long> ownedUnlockables = userUnlockableRepository.findUnlockablesByUserId(user.getIdUser()).stream().map(Unlockable::getIdUnlockable).collect(Collectors.toList());
        List<Unlockable> possibleUnlockables = new ArrayList<>();
        possibleUnlockables.addAll(unlockableRepository.findByUnlockerTypeAndUnlockerValue(UnlockerTypeConstants.POINTS.getValue(), request.getPoints()));
        possibleUnlockables.addAll(unlockableRepository.findByUnlockerTypeAndUnlockerValue(UnlockerTypeConstants.CONCERTS.getValue(), userStatistics.getConcertsOrchestrated()));
        possibleUnlockables.addAll(unlockableRepository.findByUnlockerTypeAndUnlockerValue(UnlockerTypeConstants.ACCURACY.getValue(), request.getAccuracyRate().intValue()));
        if (response.getNewRank() != null) {
            possibleUnlockables.addAll(unlockableRepository.findByUnlockerTypeAndUnlockerValue(UnlockerTypeConstants.RANK.getValue(), response.getNewRank().getLevel()));
        }

        List<Unlockable> objectsToUnlock = possibleUnlockables.stream()
                .filter(object -> !ownedUnlockables.contains(object.getIdUnlockable()))
                .collect(Collectors.toList());

        if (!objectsToUnlock.isEmpty()) {
            for (Unlockable unlockable : objectsToUnlock) {
                UserUnlockableCreateRequestDto userUnlockableCreateRequestDto = new UserUnlockableCreateRequestDto();
                userUnlockableCreateRequestDto.setIdUnlockable(unlockable.getIdUnlockable());
                userUnlockableCreateRequestDto.setIdUser(user.getIdUser());

                UserUnlockable userUnlockable = userUnlockableUtils.buildDomainFromCreateRequestDto(userUnlockableCreateRequestDto);
                userUnlockableRepository.save(userUnlockable);
            }
            response.setUnlockedObjects(objectsToUnlock);
        }
    }
    // endregion Private Functions
}
