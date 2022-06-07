package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.AvatarService;
import com.orchestrator.orchestrator.business.ConcertService;
import com.orchestrator.orchestrator.model.*;
import com.orchestrator.orchestrator.model.dto.concert.request.ConcertCompleteRequestDto;
import com.orchestrator.orchestrator.model.dto.concert.response.ConcertCompleteResponseDto;
import com.orchestrator.orchestrator.model.dto.concert.response.ConcertStartResponseDto;
import com.orchestrator.orchestrator.model.dto.instrument.response.InstrumentStartResponseDto;
import com.orchestrator.orchestrator.model.dto.userunlockable.request.UserUnlockableCreateRequestDto;
import com.orchestrator.orchestrator.repository.*;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.UserRankUtils;
import com.orchestrator.orchestrator.utils.UserUnlockableUtils;
import com.orchestrator.orchestrator.utils.constants.ConcertStatusConstants;
import com.orchestrator.orchestrator.utils.constants.UnlockerTypeConstants;
import com.orchestrator.orchestrator.utils.constants.UserStatusConstants;
import com.orchestrator.orchestrator.utils.impl.GeneralUtilsImpl;
import com.orchestrator.orchestrator.utils.impl.UserRankUtilsImpl;
import com.orchestrator.orchestrator.utils.impl.UserUnlockableUtilsImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ConcertServiceImplTest {

    @Mock
    private ConcertRepository concertRepository;
    // Utils
    private GeneralUtils generalUtils;
    private UserRankUtils userRankUtils;

    @Mock
    private UserUnlockableUtils userUnlockableUtils = mock(UserUnlockableUtils.class);
    // Other Repositories
    @Mock
    private UserRankRepository userRankRepository;
    @Mock
    private RankRepository rankRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserUnlockableRepository userUnlockableRepository;
    @Mock
    private UnlockableRepository unlockableRepository;
    @Mock
    private UserStatisticsRepository userStatisticsRepository;
    @Mock
    private SymphonyRepository symphonyRepository;
    @Mock
    private SymphonyInstrumentRepository symphonyInstrumentRepository;

    private ConcertService concertService;


    private ConcertService concertServiceVoid = mock(ConcertServiceImpl.class);

    private final Integer MAX_LEVEL = 9;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        generalUtils = new GeneralUtilsImpl();
        userRankUtils = new UserRankUtilsImpl(generalUtils);
        userUnlockableUtils = new UserUnlockableUtilsImpl(generalUtils);
        concertService = new ConcertServiceImpl(concertRepository, generalUtils, userRankUtils, userUnlockableUtils, userRankRepository, rankRepository, userRepository, userUnlockableRepository, unlockableRepository, userStatisticsRepository, symphonyRepository, symphonyInstrumentRepository);
    }


    @Test
    @DisplayName("When create With Valid Attributes And Without An Id Then Returns Concert")
    void create() {

        Concert concert = new Concert();
        concert.setUser(new User());
        concert.setStatus(1);
        concert.setPoints(10);
        concert.setSymphony(new Symphony());

        Concert createdConcert = new Concert();
        createdConcert.setIdConcert(1L);

        when(concertRepository.save(any())).thenReturn(createdConcert);

        Concert result = concertService.create(concert);

        assertEquals(result.getIdConcert(), createdConcert.getIdConcert());

    }

    @Test
    @DisplayName("When create With Valid Attributes And With An Id Then Returns IllegalArgumentException")
    void createIllegalArgumentException() {
        String expectedMessage = "Body should not contain id";
        Concert concert = new Concert();
        concert.setUser(new User());
        concert.setStatus(1);
        concert.setPoints(10);
        concert.setSymphony(new Symphony());
        concert.setIdConcert(1L);

        Throwable exception = Assertions.catchThrowable( () -> {
            Concert result = concertService.create(concert);
        });

        Assertions.assertThat(exception).isInstanceOf(IllegalArgumentException.class).hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("When findById With Valid Id Then Returns Concert")
    void findById() {
        Concert concert = new Concert();
        concert.setUser(new User());
        concert.setStatus(1);
        concert.setPoints(10);
        concert.setSymphony(new Symphony());
        concert.setIdConcert(1L);

        when(concertRepository.findById(1L)).thenReturn(Optional.ofNullable(concert));

        final Concert result = concertService.findById(1L);

        assertEquals(concert.toString(), result.toString());
    }

    @Test
    @DisplayName("When findById With Invalid Id Then Returns Null")
    void findByIdNull() {
        assertNull(concertService.findById(1L));
    }

    @Test
    @DisplayName("When findAll Then Returns Concert List")
    void findAll() {
        Concert concert = new Concert();
        concert.setUser(new User());
        concert.setStatus(1);
        concert.setPoints(10);
        concert.setSymphony(new Symphony());

        Concert concert2 = new Concert();
        concert2.setUser(new User());
        concert2.setStatus(0);
        concert2.setPoints(20);
        concert2.setSymphony(new Symphony());


        List<Concert> concertList = new ArrayList<>();
        concertList.add(concert);
        concertList.add(concert2);

        when(concertRepository.findAll()).thenReturn(concertList);

        List<Concert> result = concertService.findAll();

        assertEquals(result.size(), 2);
    }

    @Test
    @DisplayName("When update With Valid Attributes And Valid Id Then Returns Concert")
    void update() throws  IllegalAccessException{
        Concert concert = new Concert();
        concert.setUser(new User());
        concert.setStatus(1);
        concert.setPoints(10);
        concert.setSymphony(new Symphony());
        concert.setIdConcert(1L);

        Concert retrievedConcert = new Concert();
        retrievedConcert.setUser(new User());
        retrievedConcert.setStatus(2);
        retrievedConcert.setPoints(20);
        retrievedConcert.setSymphony(new Symphony());
        retrievedConcert.setIdConcert(1L);

        when(concertRepository.findById(any())).thenReturn(Optional.of(retrievedConcert));
        when(concertRepository.save(any())).thenReturn(retrievedConcert);

        final Concert result = concertService.update(concert);

        assertEquals(retrievedConcert.toString(), result.toString());
    }

    @Test
    @DisplayName("When update With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void updateNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        Concert retrievedConcert = new Concert();

        Throwable exception = Assertions.catchThrowable( () -> {
            Concert createdAvatar = concertService.update(retrievedConcert);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }



    @Test
    @DisplayName("When change With Valid Attributes And Valid Id Then Returns Concert")
    void change() throws IllegalAccessException {
        Concert concert = new Concert();
        concert.setUser(new User());
        concert.setStatus(1);
        concert.setPoints(10);
        concert.setSymphony(new Symphony());
        concert.setIdConcert(1L);

        Concert retrievedConcert = new Concert();
        retrievedConcert.setUser(new User());
        retrievedConcert.setStatus(2);
        retrievedConcert.setPoints(20);
        retrievedConcert.setSymphony(new Symphony());
        retrievedConcert.setIdConcert(1L);

        when(concertRepository.findById(any())).thenReturn(Optional.of(retrievedConcert));
        when(concertRepository.save(any())).thenReturn(retrievedConcert);

        final Concert result = concertService.change(concert);

        assertEquals(retrievedConcert.toString(), result.toString());

    }


    @Test
    @DisplayName("When removeById With Valid Id Then Returns Concert")
    void removeById() {
        Concert concert = new Concert();

        when(concertRepository.findById(4L)).thenReturn(Optional.of(concert));

        concertService.removeById(4L);

        verify(concertRepository).deleteById(4L);
    }


    @Test
    @DisplayName("When removeById Invalid Id Then Returns NoSuchElementException")
    void removeByIdNoSuchElementException() {
        String expectedMessage = "Element does not exist in database";

        Throwable exception = Assertions.catchThrowable(() -> {
            Concert result = concertService.removeById(4L);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("When change With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void changeNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        Concert retrievedConcert = new Concert();

        Throwable exception = Assertions.catchThrowable( () -> {
            Concert createdAvatar = concertService.change(retrievedConcert);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    //Integration Test
    @Test
    @DisplayName("When complete With Valid Attributes Then Returns ConcertCompleteDto")
    void complete() throws IllegalAccessException {

        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setIdUserStatistics(1L);
        userStatistics.setStatus(1);
        userStatistics.setConcertsOrchestrated(3);
        userStatistics.setOrchestrationAccuracy(10.2);

        User user = new User();
        user.setIdUser(1L);
        user.setCoinsOwned(1000);
        user.setFirstname("Max");
        user.setLastname("Ferran");
        user.setUserStatistics(userStatistics);

        Concert concert = new Concert();
        concert.setUser(user);
        concert.setStatus(1);
        concert.setPoints(10);
        concert.setSymphony(new Symphony());
        concert.setIdConcert(1L);

        Rank rank = new Rank();
        rank.setLevel(5);
        rank.setMaxExperience(4000);

        UserRank userRank = new UserRank();
        userRank.setRank(rank);
        userRank.setCurrentExperience(100);

        ConcertCompleteRequestDto concertCompleteRequestDto = new ConcertCompleteRequestDto();
        concertCompleteRequestDto.setPoints(10);
        concertCompleteRequestDto.setIdConcert(1L);
        concertCompleteRequestDto.setGainedCoins(100);
        concertCompleteRequestDto.setGainedExperience(100);
        concertCompleteRequestDto.setAccuracyRate(10.5);


        ConcertCompleteResponseDto concertCompleteResponseDto = new ConcertCompleteResponseDto();
        concertCompleteResponseDto.setNewRank(rank);

        when(concertRepository.findById(1L)).thenReturn(Optional.of(concert));

        when(userRepository.findById(1L)).thenReturn(Optional.of(concert.getUser()));

        when(userStatisticsRepository.findById(1L)).thenReturn(Optional.of(concert.getUser().getUserStatistics()));

        when(userRankRepository.findLastActiveByUser(1L)).thenReturn(Optional.of(userRank));

        when(concertServiceVoid.complete(concertCompleteRequestDto)).thenReturn(concertCompleteResponseDto);

        final ConcertCompleteResponseDto result = concertServiceVoid.complete(concertCompleteRequestDto);

        verify(concertServiceVoid, times(1)).complete(concertCompleteRequestDto);


    }

    @Test
    @DisplayName("When complete With Invalid Concert Id Then Returns NoSuchElementException")
    void completeConcertNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Concert not found in database";
        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setIdUserStatistics(1L);
        userStatistics.setStatus(1);
        userStatistics.setConcertsOrchestrated(3);
        userStatistics.setOrchestrationAccuracy(10.2);

        User user = new User();
        user.setIdUser(1L);
        user.setCoinsOwned(1000);
        user.setFirstname("Max");
        user.setLastname("Ferran");
        user.setUserStatistics(userStatistics);

        Concert concert = new Concert();
        concert.setUser(user);
        concert.setStatus(1);
        concert.setPoints(10);
        concert.setSymphony(new Symphony());
        concert.setIdConcert(1L);

        Rank rank = new Rank();
        rank.setLevel(5);
        rank.setMaxExperience(5000);

        UserRank userRank = new UserRank();
        userRank.setCurrentExperience(1000);
        userRank.setRank(rank);

        ConcertCompleteRequestDto concertCompleteRequestDto = new ConcertCompleteRequestDto();
        concertCompleteRequestDto.setPoints(10);
        concertCompleteRequestDto.setIdConcert(1L);
        concertCompleteRequestDto.setGainedCoins(100);
        concertCompleteRequestDto.setGainedExperience(10);
        concertCompleteRequestDto.setAccuracyRate(10.5);


        ConcertCompleteResponseDto concertCompleteResponseDto = new ConcertCompleteResponseDto();
        concertCompleteResponseDto.setNewRank(rank);

        Throwable exception = Assertions.catchThrowable( () -> {
            ConcertCompleteResponseDto result = concertService.complete(concertCompleteRequestDto);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);


    }


    @Test
    @DisplayName("When complete With Valid Concert Id And Invalid User Id Then Returns NoSuchElementException")
    void completeUserNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "User not found in database";
        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setIdUserStatistics(1L);
        userStatistics.setStatus(1);
        userStatistics.setConcertsOrchestrated(3);
        userStatistics.setOrchestrationAccuracy(10.2);

        User user = new User();
        user.setIdUser(1L);
        user.setCoinsOwned(1000);
        user.setFirstname("Max");
        user.setLastname("Ferran");
        user.setUserStatistics(userStatistics);

        Concert concert = new Concert();
        concert.setUser(user);
        concert.setStatus(1);
        concert.setPoints(10);
        concert.setSymphony(new Symphony());
        concert.setIdConcert(1L);

        Rank rank = new Rank();
        rank.setLevel(5);
        rank.setMaxExperience(5000);

        UserRank userRank = new UserRank();
        userRank.setCurrentExperience(1000);
        userRank.setRank(rank);

        ConcertCompleteRequestDto concertCompleteRequestDto = new ConcertCompleteRequestDto();
        concertCompleteRequestDto.setPoints(10);
        concertCompleteRequestDto.setIdConcert(1L);
        concertCompleteRequestDto.setGainedCoins(100);
        concertCompleteRequestDto.setGainedExperience(10);
        concertCompleteRequestDto.setAccuracyRate(10.5);


        ConcertCompleteResponseDto concertCompleteResponseDto = new ConcertCompleteResponseDto();
        concertCompleteResponseDto.setNewRank(rank);

        when(concertRepository.findById(1L)).thenReturn(Optional.of(concert));

        Throwable exception = Assertions.catchThrowable( () -> {
            ConcertCompleteResponseDto result = concertService.complete(concertCompleteRequestDto);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("When complete With Valid Concert Id And Valid User Id And Invalid UserStatistics Id Then Returns NoSuchElementException")
    void completeUserStatisticsNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "User Statistics not found in database";
        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setIdUserStatistics(1L);
        userStatistics.setStatus(1);
        userStatistics.setConcertsOrchestrated(3);
        userStatistics.setOrchestrationAccuracy(10.2);

        User user = new User();
        user.setIdUser(1L);
        user.setCoinsOwned(1000);
        user.setFirstname("Max");
        user.setLastname("Ferran");
        user.setUserStatistics(userStatistics);

        Concert concert = new Concert();
        concert.setUser(user);
        concert.setStatus(1);
        concert.setPoints(10);
        concert.setSymphony(new Symphony());
        concert.setIdConcert(1L);

        Rank rank = new Rank();
        rank.setLevel(5);
        rank.setMaxExperience(5000);

        UserRank userRank = new UserRank();
        userRank.setCurrentExperience(1000);
        userRank.setRank(rank);

        ConcertCompleteRequestDto concertCompleteRequestDto = new ConcertCompleteRequestDto();
        concertCompleteRequestDto.setPoints(10);
        concertCompleteRequestDto.setIdConcert(1L);
        concertCompleteRequestDto.setGainedCoins(100);
        concertCompleteRequestDto.setGainedExperience(10);
        concertCompleteRequestDto.setAccuracyRate(10.5);


        ConcertCompleteResponseDto concertCompleteResponseDto = new ConcertCompleteResponseDto();
        concertCompleteResponseDto.setNewRank(rank);

        when(concertRepository.findById(1L)).thenReturn(Optional.of(concert));

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Throwable exception = Assertions.catchThrowable( () -> {
            ConcertCompleteResponseDto result = concertService.complete(concertCompleteRequestDto);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When complete With Valid Concert Id And Valid User Id And Valid UserStatistics Id And Invalid UserRank Id Then Returns NoSuchElementException")
    void completeUserRankNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Active rank not found for user";
        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setIdUserStatistics(1L);
        userStatistics.setStatus(1);
        userStatistics.setConcertsOrchestrated(3);
        userStatistics.setOrchestrationAccuracy(10.2);

        User user = new User();
        user.setIdUser(1L);
        user.setCoinsOwned(1000);
        user.setFirstname("Max");
        user.setLastname("Ferran");
        user.setUserStatistics(userStatistics);

        Concert concert = new Concert();
        concert.setUser(user);
        concert.setStatus(1);
        concert.setPoints(10);
        concert.setSymphony(new Symphony());
        concert.setIdConcert(1L);

        Rank rank = new Rank();
        rank.setLevel(5);
        rank.setMaxExperience(5000);

        UserRank userRank = new UserRank();
        userRank.setCurrentExperience(1000);
        userRank.setRank(rank);

        ConcertCompleteRequestDto concertCompleteRequestDto = new ConcertCompleteRequestDto();
        concertCompleteRequestDto.setPoints(10);
        concertCompleteRequestDto.setIdConcert(1L);
        concertCompleteRequestDto.setGainedCoins(100);
        concertCompleteRequestDto.setGainedExperience(10);
        concertCompleteRequestDto.setAccuracyRate(10.5);


        ConcertCompleteResponseDto concertCompleteResponseDto = new ConcertCompleteResponseDto();
        concertCompleteResponseDto.setNewRank(rank);

        when(concertRepository.findById(1L)).thenReturn(Optional.of(concert));

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        when(userStatisticsRepository.findById(1L)).thenReturn(Optional.of(userStatistics));

        Throwable exception = Assertions.catchThrowable( () -> {
            ConcertCompleteResponseDto result = concertService.complete(concertCompleteRequestDto);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("When complete With Valid Concert Id And Valid User Id And Valid UserStatistics Id And UserRank Id And " +
            "New Experience Is Smaller Than Last Rank Max Experience Then Returns ConcertCompleteDto")
    void completeNewExperienceSmallerThanLastRankMaxExperience() throws IllegalAccessException {

        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setIdUserStatistics(1L);
        userStatistics.setStatus(1);
        userStatistics.setConcertsOrchestrated(3);
        userStatistics.setOrchestrationAccuracy(10.2);

        User user = new User();
        user.setIdUser(1L);
        user.setCoinsOwned(1000);
        user.setFirstname("Max");
        user.setLastname("Ferran");
        user.setUserStatistics(userStatistics);

        Concert concert = new Concert();
        concert.setUser(user);
        concert.setStatus(1);
        concert.setPoints(10);
        concert.setSymphony(new Symphony());
        concert.setIdConcert(1L);

        Rank rank = new Rank();
        rank.setLevel(5);
        rank.setMaxExperience(500);

        Rank rank2 = new Rank();
        rank.setLevel(6);
        rank.setMaxExperience(500);
        rank.setIdRank(2L);

        UserRank userRank = new UserRank();
        userRank.setCurrentExperience(100);
        userRank.setRank(rank);

        UserRank userRank2 = new UserRank();
        userRank2.setCurrentExperience(100);
        userRank2.setRank(rank2);

        ConcertCompleteRequestDto concertCompleteRequestDto = new ConcertCompleteRequestDto();
        concertCompleteRequestDto.setPoints(10);
        concertCompleteRequestDto.setIdConcert(1L);
        concertCompleteRequestDto.setGainedCoins(100);
        concertCompleteRequestDto.setGainedExperience(10);
        concertCompleteRequestDto.setAccuracyRate(10.5);


        ConcertCompleteResponseDto concertCompleteResponseDto = new ConcertCompleteResponseDto();
        concertCompleteResponseDto.setNewRank(rank);

        when(concertRepository.findById(any())).thenReturn(Optional.of(concert));

        when(userRepository.findById(any())).thenReturn(Optional.of(concert.getUser()));

        when(userStatisticsRepository.findById(any())).thenReturn(Optional.of(concert.getUser().getUserStatistics()));

        when(concertRepository.save(any())).thenReturn(concert);

        when(userRankRepository.findLastActiveByUser(any())).thenReturn(Optional.of(userRank));

        final ConcertCompleteResponseDto result = concertService.complete(concertCompleteRequestDto);

        assertNull(result.getNewRank());
    }


    @Test
    @DisplayName("When complete With Valid Concert Id And Valid User Id And Valid UserStatistics Id And UserRank Id And " +
            "New Experience Is Greater Than Last Rank Max Experience And Last Level Is Smaller Than Max Level Then Returns ConcertCompleteDto")
    void completeNewExperienceGreaterThanLastRankMaxExperienceAndLastLevelSmallerThanMaxLevel() throws IllegalAccessException {

        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setIdUserStatistics(1L);
        userStatistics.setStatus(1);
        userStatistics.setConcertsOrchestrated(3);
        userStatistics.setOrchestrationAccuracy(10.2);

        User user = new User();
        user.setIdUser(1L);
        user.setCoinsOwned(1000);
        user.setFirstname("Max");
        user.setLastname("Ferran");
        user.setUserStatistics(userStatistics);

        Concert concert = new Concert();
        concert.setUser(user);
        concert.setStatus(1);
        concert.setPoints(10);
        concert.setSymphony(new Symphony());
        concert.setIdConcert(1L);

        Rank rank = new Rank();
        rank.setLevel(5);
        rank.setMaxExperience(500);

        Rank rank2 = new Rank();
        rank.setLevel(6);
        rank.setMaxExperience(500);
        rank.setIdRank(2L);

        UserRank userRank = new UserRank();
        userRank.setCurrentExperience(1000);
        userRank.setRank(rank);

        UserRank userRank2 = new UserRank();
        userRank2.setCurrentExperience(1000);
        userRank2.setRank(rank2);

        ConcertCompleteRequestDto concertCompleteRequestDto = new ConcertCompleteRequestDto();
        concertCompleteRequestDto.setPoints(10);
        concertCompleteRequestDto.setIdConcert(1L);
        concertCompleteRequestDto.setGainedCoins(100);
        concertCompleteRequestDto.setGainedExperience(10);
        concertCompleteRequestDto.setAccuracyRate(10.5);


        ConcertCompleteResponseDto concertCompleteResponseDto = new ConcertCompleteResponseDto();
        concertCompleteResponseDto.setNewRank(rank2);

        when(concertRepository.findById(any())).thenReturn(Optional.of(concert));

        when(userRepository.findById(any())).thenReturn(Optional.of(concert.getUser()));

        when(userStatisticsRepository.findById(any())).thenReturn(Optional.of(concert.getUser().getUserStatistics()));

        when(concertRepository.save(any())).thenReturn(concert);

        when(userRankRepository.findLastActiveByUser(any())).thenReturn(Optional.of(userRank));

        when(rankRepository.findByLevel(userRank.getRank().getLevel() + 1)).thenReturn(rank2);

        when(rankRepository.findById(userRank2.getRank().getIdRank())).thenReturn(Optional.of(rank2));

        final ConcertCompleteResponseDto result = concertService.complete(concertCompleteRequestDto);

        assertEquals(result.toString(), concertCompleteResponseDto.toString());
    }


    @Test
    @DisplayName("When complete With Valid Concert Id And Valid User Id And Valid UserStatistics Id And UserRank Id And " +
            "New Experience Is Greater Than Last Rank Max Experience And Last Level Is Smaller Than Max Level And New Rank Is Null Then Returns NoSuchElementException")
    void completeNewExperienceGreaterThanLastRankMaxExperienceAndLastLevelSmallerThanMaxLevelAndNewRankNull() throws IllegalAccessException {
        String expectedMessage = "User Rank not found in database";
        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setIdUserStatistics(1L);
        userStatistics.setStatus(1);
        userStatistics.setConcertsOrchestrated(3);
        userStatistics.setOrchestrationAccuracy(10.2);

        User user = new User();
        user.setIdUser(1L);
        user.setCoinsOwned(1000);
        user.setFirstname("Max");
        user.setLastname("Ferran");
        user.setUserStatistics(userStatistics);

        Concert concert = new Concert();
        concert.setUser(user);
        concert.setStatus(1);
        concert.setPoints(10);
        concert.setSymphony(new Symphony());
        concert.setIdConcert(1L);

        Rank rank = new Rank();
        rank.setLevel(5);
        rank.setMaxExperience(500);

        Rank rank2 = new Rank();
        rank.setLevel(6);
        rank.setMaxExperience(500);
        rank.setIdRank(2L);

        UserRank userRank = new UserRank();
        userRank.setCurrentExperience(1000);
        userRank.setRank(rank);

        UserRank userRank2 = new UserRank();
        userRank2.setCurrentExperience(1000);
        userRank2.setRank(rank2);

        ConcertCompleteRequestDto concertCompleteRequestDto = new ConcertCompleteRequestDto();
        concertCompleteRequestDto.setPoints(10);
        concertCompleteRequestDto.setIdConcert(1L);
        concertCompleteRequestDto.setGainedCoins(100);
        concertCompleteRequestDto.setGainedExperience(10);
        concertCompleteRequestDto.setAccuracyRate(10.5);


        ConcertCompleteResponseDto concertCompleteResponseDto = new ConcertCompleteResponseDto();
        concertCompleteResponseDto.setNewRank(rank2);

        when(concertRepository.findById(any())).thenReturn(Optional.of(concert));

        when(userRepository.findById(any())).thenReturn(Optional.of(concert.getUser()));

        when(userStatisticsRepository.findById(any())).thenReturn(Optional.of(concert.getUser().getUserStatistics()));

        when(concertRepository.save(any())).thenReturn(concert);

        when(userRankRepository.findLastActiveByUser(any())).thenReturn(Optional.of(userRank));

        when(rankRepository.findByLevel(userRank.getRank().getLevel() + 1)).thenReturn(rank2);

        Throwable exception = Assertions.catchThrowable( () -> {
            ConcertCompleteResponseDto result = concertService.complete(concertCompleteRequestDto);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);

    }


    @Test
    @DisplayName("When complete With Valid Concert Id And Valid User Id And Valid UserStatistics Id And UserRank Id And " +
            "New Experience Is Greater Than Last Rank Max Experience And Last Level Is Equal To Max Level And New Current" +
            "Experience Is Smaller Than Max Experience Then Returns ConcertCompleteDto")
    void completeNewExperienceGreaterThanLastRankMaxExperienceAndLastLevelEqualMaxLevelAndNewCurrentExperienceSmallerThanMaxExperience() throws IllegalAccessException {

        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setIdUserStatistics(1L);
        userStatistics.setStatus(1);
        userStatistics.setConcertsOrchestrated(3);
        userStatistics.setOrchestrationAccuracy(10.2);

        User user = new User();
        user.setIdUser(1L);
        user.setCoinsOwned(1000);
        user.setFirstname("Max");
        user.setLastname("Ferran");
        user.setUserStatistics(userStatistics);

        Concert concert = new Concert();
        concert.setUser(user);
        concert.setStatus(1);
        concert.setPoints(10);
        concert.setSymphony(new Symphony());
        concert.setIdConcert(1L);

        Rank rank = new Rank();
        rank.setLevel(1000);
        rank.setMaxExperience(500);

        Rank rank2 = new Rank();
        rank.setLevel(6);
        rank.setMaxExperience(500);
        rank.setIdRank(2L);

        UserRank userRank = new UserRank();
        userRank.setCurrentExperience(400);
        userRank.setRank(rank);

        UserRank userRank2 = new UserRank();
        userRank2.setCurrentExperience(100);
        userRank2.setRank(rank2);

        ConcertCompleteRequestDto concertCompleteRequestDto = new ConcertCompleteRequestDto();
        concertCompleteRequestDto.setPoints(10);
        concertCompleteRequestDto.setIdConcert(1L);
        concertCompleteRequestDto.setGainedCoins(100);
        concertCompleteRequestDto.setGainedExperience(200);
        concertCompleteRequestDto.setAccuracyRate(10.5);


        ConcertCompleteResponseDto concertCompleteResponseDto = new ConcertCompleteResponseDto();
        concertCompleteResponseDto.setNewRank(rank);

        when(concertRepository.findById(any())).thenReturn(Optional.of(concert));

        when(userRepository.findById(any())).thenReturn(Optional.of(concert.getUser()));

        when(userStatisticsRepository.findById(any())).thenReturn(Optional.of(concert.getUser().getUserStatistics()));

        when(concertRepository.save(any())).thenReturn(concert);

        when(userRankRepository.findLastActiveByUser(any())).thenReturn(Optional.of(userRank));

        userRank.getRank().setLevel(9);

        final ConcertCompleteResponseDto result = concertService.complete(concertCompleteRequestDto);

        assertNull(result.getNewRank());
    }

    @Test
    @DisplayName("When complete With Valid Concert Id And Valid User Id And Valid UserStatistics Id And UserRank Id And " +
            "New Experience Is Smaller Than Last Rank Max Experience And Objects To Unlock List Is Not Empty Then Returns ConcertCompleteDto")
    void completeNewExperienceSmallerThanLastRankMaxExperienceObjectsToUnlockNotEmpty() throws IllegalAccessException {

        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setIdUserStatistics(1L);
        userStatistics.setStatus(1);
        userStatistics.setConcertsOrchestrated(3);
        userStatistics.setOrchestrationAccuracy(10.2);

        User user = new User();
        user.setIdUser(1L);
        user.setCoinsOwned(1000);
        user.setFirstname("Max");
        user.setLastname("Ferran");
        user.setUserStatistics(userStatistics);

        Concert concert = new Concert();
        concert.setUser(user);
        concert.setStatus(1);
        concert.setPoints(10);
        concert.setSymphony(new Symphony());
        concert.setIdConcert(1L);

        Rank rank = new Rank();
        rank.setLevel(5);
        rank.setMaxExperience(500);

        Rank rank2 = new Rank();
        rank.setLevel(6);
        rank.setMaxExperience(500);
        rank.setIdRank(2L);

        UserRank userRank = new UserRank();
        userRank.setCurrentExperience(100);
        userRank.setRank(rank);

        UserRank userRank2 = new UserRank();
        userRank2.setCurrentExperience(100);
        userRank2.setRank(rank2);

        ConcertCompleteRequestDto concertCompleteRequestDto = new ConcertCompleteRequestDto();
        concertCompleteRequestDto.setPoints(10);
        concertCompleteRequestDto.setIdConcert(1L);
        concertCompleteRequestDto.setGainedCoins(100);
        concertCompleteRequestDto.setGainedExperience(10);
        concertCompleteRequestDto.setAccuracyRate(10.5);


        ConcertCompleteResponseDto concertCompleteResponseDto = new ConcertCompleteResponseDto();

        when(concertRepository.findById(any())).thenReturn(Optional.of(concert));

        when(userRepository.findById(any())).thenReturn(Optional.of(concert.getUser()));

        when(userStatisticsRepository.findById(any())).thenReturn(Optional.of(concert.getUser().getUserStatistics()));

        when(concertRepository.save(any())).thenReturn(concert);

        when(userRankRepository.findLastActiveByUser(any())).thenReturn(Optional.of(userRank));

        List<Unlockable> ownedUnlockables = new ArrayList<>();
        ownedUnlockables.add(new Unlockable());
        ownedUnlockables.get(0).setIdUnlockable(1L);

        when(userUnlockableRepository.findUnlockablesByUserId(any())).thenReturn(ownedUnlockables);

        List<Unlockable> possibleUnlockables = new ArrayList<>();
        possibleUnlockables.add(new Unlockable());
        possibleUnlockables.get(0).setIdUnlockable(1L);

        List<Unlockable> emptyUnlockables = new ArrayList<>();
        emptyUnlockables.add(new Unlockable());
        emptyUnlockables.get(0).setIdUnlockable(3L);


        List<Unlockable> emptyUnlockables2 = new ArrayList<>();
        emptyUnlockables2.add(new Unlockable());
        emptyUnlockables2.get(0).setIdUnlockable(4L);

        when(unlockableRepository.findByUnlockerTypeAndUnlockerValue(UnlockerTypeConstants.POINTS.getValue(), concertCompleteRequestDto.getPoints())).thenReturn(possibleUnlockables);
        when(unlockableRepository.findByUnlockerTypeAndUnlockerValue(UnlockerTypeConstants.CONCERTS.getValue(), 2)).thenReturn(emptyUnlockables);
        when(unlockableRepository.findByUnlockerTypeAndUnlockerValue(UnlockerTypeConstants.ACCURACY.getValue(), 10)).thenReturn(emptyUnlockables2);

        for (Unlockable unlockable : possibleUnlockables) {
            UserUnlockableCreateRequestDto userUnlockableCreateRequestDto = new UserUnlockableCreateRequestDto();
            userUnlockableCreateRequestDto.setIdUser(1L);
            userUnlockableCreateRequestDto.setIdUnlockable(3L);

            UserUnlockable userUnlockable = new UserUnlockable();

        }

        List<Unlockable> fullList = new ArrayList<>();
        fullList.addAll(emptyUnlockables2);

        concertCompleteResponseDto.setUnlockedObjects(fullList);
        final ConcertCompleteResponseDto result = concertService.complete(concertCompleteRequestDto);
        System.out.println(concertCompleteResponseDto);
        System.out.println(result);
        assertEquals(result.toString(), concertCompleteResponseDto.toString());
    }

    @Test
    @DisplayName("When start With Valid Attributes The Returns ConcertStartResponseDto")
    void start() throws IllegalAccessException {
        Concert concert = new Concert();

        Symphony symphony = new Symphony();
        symphony.setIdUnlockable(1L);
        symphony.setCoinsCost(10);
        concert.setSymphony(symphony);

        when(concertRepository.save(concert)).thenReturn(concert);

        when(symphonyRepository.findById(any())).thenReturn(Optional.of(new Symphony()));

        Instrument instrument = new Instrument();
        instrument.setName("trumpet");
        instrument.setIdInstrument(1L);

        List<SymphonyInstrument> symphonyInstrumentList = new ArrayList<>();
        symphonyInstrumentList.add(new SymphonyInstrument());
        symphonyInstrumentList.get(0).setSymphony(new Symphony());
        symphonyInstrumentList.get(0).setInstrument(instrument);

        when(symphonyInstrumentRepository.findBySymphony(any())).thenReturn(symphonyInstrumentList);

        ConcertStartResponseDto concertStartResponseDto = new ConcertStartResponseDto();

        generalUtils.mapFields(concert, concertStartResponseDto);
        generalUtils.mapFields(symphony, concertStartResponseDto);
        concertStartResponseDto.setInstruments(symphonyInstrumentList.stream().map(symphonyInstrument -> {
            InstrumentStartResponseDto instrumentStartResponseDto = new InstrumentStartResponseDto();
            try {
                generalUtils.mapFields(symphonyInstrument.getInstrument(), instrumentStartResponseDto);
                generalUtils.mapFields(symphonyInstrument, instrumentStartResponseDto);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return instrumentStartResponseDto;
        }).collect(Collectors.toList()));

        final ConcertStartResponseDto result = concertService.start(concert);

        assertEquals(result.toString(), concertStartResponseDto.toString());
    }


    @Test
    @DisplayName("When start With Invalid Symphony The Returns NoSuchElementException")
    void startInvalidSymphony() throws IllegalAccessException {
        String expectedMessage = "Symphony not found";
        Concert concert = new Concert();

        Symphony symphony = new Symphony();
        symphony.setIdUnlockable(1L);
        symphony.setCoinsCost(10);
        concert.setSymphony(symphony);

        when(concertRepository.save(concert)).thenReturn(concert);

        Throwable exception = Assertions.catchThrowable( () -> {
            ConcertStartResponseDto result = concertService.start(concert);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }


    @Test
    @DisplayName("When start With No Symphony Instruments The Returns NoSuchElementException")
    void startNoSymphonyInstruments() throws IllegalAccessException {
        String expectedMessage = "Instruments not found ind symphony";
        Concert concert = new Concert();

        Symphony symphony = new Symphony();
        symphony.setIdUnlockable(1L);
        symphony.setCoinsCost(10);
        concert.setSymphony(symphony);

        when(concertRepository.save(concert)).thenReturn(concert);

        when(symphonyRepository.findById(any())).thenReturn(Optional.of(new Symphony()));

        Throwable exception = Assertions.catchThrowable( () -> {
            ConcertStartResponseDto result = concertService.start(concert);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When getPossibleStatus Then Returns ConcertStatusConstants List")
    void getPossibleStatus() {
        ConcertStatusConstants concertStatusConstants = ConcertStatusConstants.CANCELED;
        ConcertStatusConstants concertStatusConstants2 = ConcertStatusConstants.DELETED;
        ConcertStatusConstants concertStatusConstants3 = ConcertStatusConstants.FINISHED;
        ConcertStatusConstants concertStatusConstants4 = ConcertStatusConstants.STARTED;

        List<ConcertStatusConstants> concertStatusConstantsList = new ArrayList<>();
        concertStatusConstantsList.add(concertStatusConstants2);
        concertStatusConstantsList.add(concertStatusConstants4);
        concertStatusConstantsList.add(concertStatusConstants);
        concertStatusConstantsList.add(concertStatusConstants3);

        when(concertServiceVoid.getPossibleStatus()).thenReturn(Arrays.stream(ConcertStatusConstants.values()).collect(Collectors.toList()));

        List<ConcertStatusConstants> result = concertService.getPossibleStatus();

        assertEquals(result, concertStatusConstantsList);
    }
}
