package com.orchestrator.orchestrator.business.impl;

import com.orchestrator.orchestrator.business.UserStatisticsService;
import com.orchestrator.orchestrator.model.UserStatistics;
import com.orchestrator.orchestrator.repository.UserStatisticsRepository;
import com.orchestrator.orchestrator.utils.GeneralUtils;
import com.orchestrator.orchestrator.utils.constants.UserStatisticsStatusConstants;
import com.orchestrator.orchestrator.utils.impl.GeneralUtilsImpl;
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

public class UserStatisticsServiceImplTest {

    @Mock
    private UserStatisticsRepository userStatisticsRepository;

    private GeneralUtils generalUtils;
    private UserStatisticsService userStatisticsService;

    @Mock
    private UserStatisticsService userStatisticsServiceMock = mock(UserStatisticsServiceImpl.class);

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        generalUtils = new GeneralUtilsImpl();
        userStatisticsService = new UserStatisticsServiceImpl(userStatisticsRepository, generalUtils) {
        };
    }

    @Test
    @DisplayName("When create With Valid Attributes And Without An Id Then Returns UserStatistics")
    void create() {
        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setStatus(1);

        UserStatistics createdUserStatistics = new UserStatistics();
        createdUserStatistics.setIdUserStatistics(1L);

        when(userStatisticsRepository.save(any())).thenReturn(createdUserStatistics);

        UserStatistics result = userStatisticsService.create(userStatistics);

        assertEquals(result.getIdUserStatistics(), createdUserStatistics.getIdUserStatistics());
    }

    @Test
    @DisplayName("When create With Valid Attributes And With An Id Then Returns IllegalArgumentException")
    void createIllegalArgumentException() throws  IllegalArgumentException{
        String expectedMessage = "Body should not contain id";
        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setStatus(1);
        userStatistics.setIdUserStatistics(1L);

        Throwable exception = Assertions.catchThrowable( () -> {
            UserStatistics result = userStatisticsService.create(userStatistics);
        });

        Assertions.assertThat(exception).isInstanceOf(IllegalArgumentException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When findById With Valid Id Then Returns UserStatistics")
    void findById() {
        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setStatus(1);

        when(userStatisticsRepository.findById(1L)).thenReturn(Optional.ofNullable(userStatistics));

        final UserStatistics result = userStatisticsService.findById(1L);

        assertEquals(result.toString(), userStatistics.toString());
    }

    @Test
    @DisplayName("When findById With Invalid Id Then Returns Null")
    void findByIdNull() {
        assertNull(userStatisticsService.findById(1L));
    }


    @Test
    @DisplayName("When findAll Then Returns UserStatistics List")
    void findAll() {
        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setStatus(1);

        UserStatistics userStatistics2 = new UserStatistics();
        userStatistics.setStatus(2);

        List<UserStatistics> userStatisticsList = new ArrayList<>();
        userStatisticsList.add(userStatistics);
        userStatisticsList.add(userStatistics2);

        when(userStatisticsRepository.findAll()).thenReturn( userStatisticsList);

        List<UserStatistics> result = userStatisticsService.findAll();

        assertEquals(result.size(), 2);
    }

    @Test
    @DisplayName("When update With Valid Attributes And Valid Id Then Returns UserStatistics")
    void update() throws IllegalAccessException {
        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setStatus(1);

        UserStatistics retrievedUserStatistics = new UserStatistics();
        retrievedUserStatistics.setIdUserStatistics(4L);
        retrievedUserStatistics.setStatus(2);

        when(userStatisticsRepository.findById(any())).thenReturn(Optional.of(retrievedUserStatistics));
        when(userStatisticsRepository.save(any())).thenReturn(retrievedUserStatistics);

        final UserStatistics result = userStatisticsService.update(userStatistics);

        assertEquals(retrievedUserStatistics.toString(), result.toString());
    }

    @Test
    @DisplayName("When update With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void updateNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        UserStatistics retrievedUserStatistics = new UserStatistics();
        retrievedUserStatistics.setIdUserStatistics(4L);
        retrievedUserStatistics.setStatus(1);

        Throwable exception = Assertions.catchThrowable( () -> {
            UserStatistics result = userStatisticsService.update(retrievedUserStatistics);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }



    @Test
    @DisplayName("When change With Valid Attributes And Valid Id Then Returns UserStatistics")
    void change() throws IllegalAccessException {
        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setStatus(1);

        UserStatistics retrievedUserStatistics = new UserStatistics();
        retrievedUserStatistics.setIdUserStatistics(4L);
        retrievedUserStatistics.setStatus(2);

        when(userStatisticsRepository.findById(any())).thenReturn(Optional.of(retrievedUserStatistics));
        when(userStatisticsRepository.save(any())).thenReturn(retrievedUserStatistics);

        final UserStatistics result = userStatisticsService.change(userStatistics);

        assertEquals(retrievedUserStatistics.toString(), result.toString());
    }

    @Test
    @DisplayName("When change With Valid Attributes And Invalid Id Then Returns NoSuchElementException")
    void changeNoSuchElementException() throws IllegalAccessException {
        String expectedMessage = "Element does not exist in database";
        UserStatistics retrievedUserStatistics = new UserStatistics();
        retrievedUserStatistics.setIdUserStatistics(4L);
        retrievedUserStatistics.setStatus(2);

        Throwable exception = Assertions.catchThrowable( () -> {
            UserStatistics result = userStatisticsService.change(retrievedUserStatistics);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When removeById With Valid Id Then Returns UserStatistics")
    void removeById(){
        UserStatistics retrievedUserStatistics = new UserStatistics();
        retrievedUserStatistics.setIdUserStatistics(4L);
        retrievedUserStatistics.setStatus(1);

        when(userStatisticsRepository.findById(4L)).thenReturn(Optional.of(retrievedUserStatistics));

        userStatisticsService.removeById(4L);
        verify(userStatisticsRepository).deleteById(4L);
    }


    @Test
    @DisplayName("When removeById Invalid Id Then Returns NoSuchElementException")
    void removeByIdNoSuchElementException() {
        String expectedMessage = "Element does not exist in database";

        Throwable exception = Assertions.catchThrowable( () -> {
            UserStatistics result = userStatisticsService.removeById(4L);
        });

        Assertions.assertThat(exception).isInstanceOf(NoSuchElementException.class).hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When getPossibleStatus Then Returns UserStatisticsStatusConstants List")
    void getPossibleStatus() {
        UserStatisticsStatusConstants userStatisticsStatusConstants = UserStatisticsStatusConstants.DELETED;
        UserStatisticsStatusConstants userStatisticsStatusConstants1 = UserStatisticsStatusConstants.ACTIVE;

        List<UserStatisticsStatusConstants> userStatisticsStatusConstantsList = new ArrayList<>();
        userStatisticsStatusConstantsList.add(userStatisticsStatusConstants);
        userStatisticsStatusConstantsList.add(userStatisticsStatusConstants1);

        when(userStatisticsServiceMock.getPossibleStatus()).thenReturn(Arrays.stream(UserStatisticsStatusConstants.values()).collect(Collectors.toList()));

        List<UserStatisticsStatusConstants> result = userStatisticsService.getPossibleStatus();

        assertEquals(result, userStatisticsStatusConstantsList);
    }
}
